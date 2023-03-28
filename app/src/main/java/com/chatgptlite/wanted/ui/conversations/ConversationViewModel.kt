package com.chatgptlite.wanted.ui.conversations

import androidx.lifecycle.ViewModel
import com.chatgptlite.wanted.models.ConversationModel
import com.chatgptlite.wanted.models.MessageModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

/**
 * Used to communicate between screens.
 */
class ConversationViewModel : ViewModel() {
    private val _currentConversation: MutableStateFlow<String> = MutableStateFlow("")
    private val _messages: MutableStateFlow<HashMap<String, List<MessageModel>>> = MutableStateFlow(hashMapOf())
    private val _isFetching: MutableStateFlow<Boolean> = MutableStateFlow(false)

    val currentConversationState = _currentConversation.asStateFlow()
    val messagesListState = _messages.asStateFlow()
    val isFetching = _isFetching.asStateFlow()

    fun initConversation(conversation: ConversationModel) {
        _isFetching.value = true
        _currentConversation.value = conversation.id
        // Fetch data here

        _isFetching.value = false
    }

    fun setMessages(messages: List<MessageModel>) {
        _messages.value[_currentConversation.value] = messages
    }
}