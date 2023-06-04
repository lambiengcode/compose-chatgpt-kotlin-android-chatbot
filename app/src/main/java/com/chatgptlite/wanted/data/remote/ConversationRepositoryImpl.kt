package com.chatgptlite.wanted.data.remote

import android.content.ContentValues
import android.util.Log
import com.chatgptlite.wanted.constants.conversationCollection
import com.chatgptlite.wanted.helpers.DataHolder
import com.chatgptlite.wanted.models.ConversationModel
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import com.google.firebase.firestore.QuerySnapshot
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class ConversationRepositoryImpl @Inject constructor(
    private val fsInstance: FirebaseFirestore,
) : ConversationRepository {
    private lateinit var result: QuerySnapshot
    override suspend fun fetchConversations(): MutableList<ConversationModel> {
        result = fsInstance.collection(conversationCollection)
            .orderBy("createdAt", Query.Direction.DESCENDING).get().await()

        if (result.documents.isNotEmpty()) {
            val documents = result.documents

            return documents.map {
                it.toObject(ConversationModel::class.java)
            }.toList() as MutableList<ConversationModel>
        }

        return mutableListOf()
    }

    override fun newConversation(conversation: ConversationModel): ConversationModel {
        fsInstance.collection(conversationCollection).add(conversation)
        return conversation
    }

    override fun deleteConversation(index: Int) {
        DataHolder.docPath = result.documents[index].id

        val docRef = fsInstance
            .collection("conversations")
            .document(DataHolder.docPath)

        // Remove the 'capital' field from the document
        val updates = hashMapOf<String, Any>(
            "id" to FieldValue.delete(),
            "title" to FieldValue.delete(),
            "createdAt" to FieldValue.delete()
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