package com.chatgptlite.wanted.models

import androidx.compose.runtime.Immutable
import java.util.Date

@Immutable
data class ConversationModel(
    val id: String,
    val title: String,
    val date: Date
)
