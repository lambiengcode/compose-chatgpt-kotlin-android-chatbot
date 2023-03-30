package com.chatgptlite.wanted.models

import java.util.*

data class MessageModel (
    var id: String = Date().time.toString(),
    var conversationId: String = "",
    var question: String = "",
    var answer: String = "",
    var createdAt: Date = Date(),
)