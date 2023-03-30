package com.chatgptlite.wanted.data.remote

import com.chatgptlite.wanted.models.MessageModel
import kotlinx.coroutines.flow.Flow

interface MessageRepository {
    fun fetchMessages(conversationId: String): Flow<List<MessageModel>>
    fun createMessage(message: MessageModel): MessageModel
    fun deleteMessage(message: MessageModel)
}