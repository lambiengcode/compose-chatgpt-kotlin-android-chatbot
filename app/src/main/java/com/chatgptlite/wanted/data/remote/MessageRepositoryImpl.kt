package com.chatgptlite.wanted.data.remote

import android.content.ContentValues
import android.util.Log
import com.chatgptlite.wanted.constants.messageCollection
import com.chatgptlite.wanted.helpers.DataHolder
import com.chatgptlite.wanted.models.MessageModel
import com.google.firebase.firestore.FieldValue
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
    private lateinit var result: QuerySnapshot
    override fun fetchMessages(conversationId: String): Flow<List<MessageModel>> =
        callbackFlow {
        result =
            fsInstance
                .collection(messageCollection)
                .whereEqualTo("conversationId", conversationId)
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

    override fun deleteMessage() {
        val docRef = fsInstance
            .collection("messages")
            .document(DataHolder.docPath)

        // Remove the fields from the document
        val updates = hashMapOf<String, Any>(
            "answer" to FieldValue.delete(),
            "conversationId" to FieldValue.delete(),
            "createdAt" to FieldValue.delete(),
            "id" to FieldValue.delete(),
            "question" to FieldValue.delete()
        )
        docRef.update(updates)
            .addOnSuccessListener {
                Log.d(
                    ContentValues.TAG,
                    "DocumentSnapshot successfully deleted from message!"
                )
            }
            .addOnFailureListener { e ->
                Log.w(
                    ContentValues.TAG,
                    "Error deleting document", e
                )
            }

    }
}