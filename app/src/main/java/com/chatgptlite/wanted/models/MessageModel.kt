package com.chatgptlite.wanted.models

import java.util.*

data class MessageModel (
    var id: String = Date().time.toString(),
    var conversationId: String = "",
    var question: String = "",
    var answer: String = "",
    var createdAt: Date = Date(),
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as MessageModel

        if (id != other.id) return false
        if (conversationId != other.conversationId) return false
        if (question != other.question) return false
        if (answer != other.answer) return false
        if (createdAt != other.createdAt) return false

        return true
    }

    override fun hashCode(): Int {
        var result = id.hashCode()
        result = 31 * result + conversationId.hashCode()
        result = 31 * result + question.hashCode()
        result = 31 * result + answer.hashCode()
        result = 31 * result + createdAt.hashCode()
        return result
    }
}