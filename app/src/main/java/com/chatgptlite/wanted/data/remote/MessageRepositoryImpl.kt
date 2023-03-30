package com.chatgptlite.wanted.data.remote

import com.chatgptlite.wanted.constants.messageCollection
import com.chatgptlite.wanted.models.MessageModel
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import com.google.firebase.firestore.QuerySnapshot
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class MessageRepositoryImpl @Inject constructor(
    private val fsInstance: FirebaseFirestore,
) : MessageRepository {
    override fun fetchMessages(conversationId: String): Flow<List<MessageModel>> = callbackFlow {
        val result: QuerySnapshot =
            fsInstance.collection(messageCollection).whereEqualTo("conversationId", conversationId)
                .orderBy("createdAt", Query.Direction.DESCENDING).get().await()

        if (result.documents.isNotEmpty()) {
            val documents = result.documents

            trySend(documents.map {
                it.toObject(MessageModel::class.java)
            }.toList() as List<MessageModel>)

            awaitClose {
                close()
            }
        } else {
            trySend(listOf())

            awaitClose {
                close()
            }
        }
    }

    override fun createMessage(message: MessageModel): MessageModel {
        fsInstance.collection(messageCollection).add(message)

        return message
    }

    override fun deleteMessage(message: MessageModel) {
        TODO("Not yet implemented")
    }
}