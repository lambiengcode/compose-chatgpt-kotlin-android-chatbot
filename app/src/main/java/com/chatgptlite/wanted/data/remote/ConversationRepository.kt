package com.chatgptlite.wanted.data.remote

import com.chatgptlite.wanted.models.ConversationModel

interface ConversationRepository {
    suspend fun fetchConversations() : MutableList<ConversationModel>
    fun newConversation(conversation: ConversationModel) : ConversationModel
    suspend fun deleteConversation(conversationId: String)
}