package com.chatgptlite.wanted.models

import androidx.compose.runtime.Immutable
import java.util.*

@Immutable
data class MessageModel (
    val message: String,
    val isMe: Boolean,
    val createdAt: Date
)