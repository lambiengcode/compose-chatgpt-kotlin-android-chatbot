@file:Suppress("UNCHECKED_CAST")

package com.chatgptlite.wanted.ui.conversations

import androidx.lifecycle.ViewModel
import com.chatgptlite.wanted.data.remote.ConversationRepository
import com.chatgptlite.wanted.data.remote.MessageRepository
import com.chatgptlite.wanted.data.remote.OpenAIRepositoryImpl
import com.chatgptlite.wanted.models.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import java.util.*
import javax.inject.Inject

/**
 * Used to communicate between screens.
 */

@HiltViewModel
class ConversationViewModel @Inject constructor(
    private val conversationRepo: ConversationRepository,
    private val messageRepo: MessageRepository,
    private val openAIRepo: OpenAIRepositoryImpl,
) : ViewModel() {
    private val _currentConversation: MutableStateFlow<String> =
        MutableStateFlow(Date().time.toString())
    private val _conversations: MutableStateFlow<MutableList<ConversationModel>> = MutableStateFlow(
        mutableListOf()
    )
    private val _messages: MutableStateFlow<HashMap<String, MutableList<MessageModel>>> =
        MutableStateFlow(HashMap())
    private val _isFetching: MutableStateFlow<Boolean> = MutableStateFlow(false)

    val currentConversationState: StateFlow<String> = _currentConversation.asStateFlow()
    val conversationsState: StateFlow<MutableList<ConversationModel>> = _conversations.asStateFlow()
    val messagesState: StateFlow<HashMap<String, MutableList<MessageModel>>> =
        _messages.asStateFlow()
    val isFetching: StateFlow<Boolean> = _isFetching.asStateFlow()

    suspend fun initialize() {
        _isFetching.value = true

        _conversations.value = conversationRepo.fetchConversations()

        if (_conversations.value.isNotEmpty()) {
            _currentConversation.value = _conversations.value.first().id
            fetchMessages()
        }

        _isFetching.value = false
    }

    suspend fun onConversation(conversation: ConversationModel) {
        _isFetching.value = true
        _currentConversation.value = conversation.id

        fetchMessages()
        _isFetching.value = false
    }

    suspend fun sendMessage(message: String) {
        if (getMessagesByConversation(_currentConversation.value).isEmpty()) {
            createConversationRemote(message)
        }

        val newMessageModel: MessageModel = MessageModel(
            question = message,
            answer = "Let me thinking...",
            conversationId = _currentConversation.value,
        )

        val currentListMessage: MutableList<MessageModel> =
            getMessagesByConversation(_currentConversation.value).toMutableList()

        // Insert message to list
        currentListMessage.add(0, newMessageModel)
        setMessages(currentListMessage)

        // Execute API OpenAI
        val flow: Flow<String> = openAIRepo.textCompletionsWithStream(
            TextCompletionsParam(
                promptText = getPrompt(_currentConversation.value),
                messagesTurbo = getMessagesParamsTurbo(_currentConversation.value)
            )
        )

        var answerFromGPT: String = ""

        flow.collect {
            answerFromGPT += it
            updateLocalAnswer(answerFromGPT.trim())
        }

        // Save to Firestore
        messageRepo.createMessage(newMessageModel.copy(answer = answerFromGPT))
    }

    private fun createConversationRemote(title: String) {
        val newConversation: ConversationModel = ConversationModel(
            id = _currentConversation.value,
            title = title,
            createdAt = Date(),
        )

        conversationRepo.newConversation(newConversation)

        val conversations = _conversations.value.toMutableList()
        conversations.add(0, newConversation)

        _conversations.value = conversations
    }

    fun newConversation() {
        val conversationId: String = Date().time.toString()

        _currentConversation.value = conversationId
    }

    private fun getMessagesByConversation(conversationId: String): MutableList<MessageModel> {
        if (_messages.value[conversationId] == null) return mutableListOf()

        val messagesMap: HashMap<String, MutableList<MessageModel>> =
            _messages.value.clone() as HashMap<String, MutableList<MessageModel>>

        return messagesMap[conversationId]!!
    }

    private fun getPrompt(conversationId: String): String {
        if (_messages.value[conversationId] == null) return ""

        val messagesMap: HashMap<String, MutableList<MessageModel>> =
            _messages.value.clone() as HashMap<String, MutableList<MessageModel>>

        var response: String = ""

        for (message in messagesMap[conversationId]!!.reversed()) {
            response += """
Human:${message.question.trim()}
Bot:${if (message.answer == "Let me thinking...") "" else message.answer.trim()}"""
        }

        return response
    }

    private fun getMessagesParamsTurbo(conversationId: String): List<MessageTurbo> {
        if (_messages.value[conversationId] == null) return listOf()

        val messagesMap: HashMap<String, MutableList<MessageModel>> =
            _messages.value.clone() as HashMap<String, MutableList<MessageModel>>

        val response: MutableList<MessageTurbo> = mutableListOf(
            MessageTurbo(
                role = TurboRole.system,
                content = "Markdown style if exists code"
            )
        )

        for (message in messagesMap[conversationId]!!.reversed()) {
            response.add(MessageTurbo(content = message.question))

            if (message.answer != "Let me thinking...") {
                response.add(MessageTurbo(content = message.answer, role = TurboRole.user))
            }
        }

        return response.toList()
    }

    private suspend fun fetchMessages() {
        if (_currentConversation.value.isEmpty() || _messages.value[_currentConversation.value] != null) return

        val flow: Flow<List<MessageModel>> = messageRepo.fetchMessages(_currentConversation.value)

        flow.collectLatest {
            setMessages(it.toMutableList())
        }
    }

    private fun updateLocalAnswer(answer: String) {
        val currentListMessage: MutableList<MessageModel> =
            getMessagesByConversation(_currentConversation.value).toMutableList()

        currentListMessage[0] = currentListMessage[0].copy(answer = answer)

        setMessages(currentListMessage)
    }

    private fun setMessages(messages: MutableList<MessageModel>) {
        val messagesMap: HashMap<String, MutableList<MessageModel>> =
            _messages.value.clone() as HashMap<String, MutableList<MessageModel>>

        messagesMap[_currentConversation.value] = messages

        _messages.value = messagesMap
    }
}