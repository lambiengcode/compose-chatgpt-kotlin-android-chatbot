package com.chatgptlite.wanted.data.remote

import com.chatgptlite.wanted.constants.conversationCollection
import com.chatgptlite.wanted.models.ConversationModel
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import com.google.firebase.firestore.QuerySnapshot
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class ConversationRepositoryImpl @Inject constructor(
    private val fsInstance: FirebaseFirestore,
) : ConversationRepository {
    override suspend fun fetchConversations(): MutableList<ConversationModel> {
        val result: QuerySnapshot = fsInstance.collection(conversationCollection)
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

    override fun deleteConversation() {
        TODO("Not yet implemented")
    }

}