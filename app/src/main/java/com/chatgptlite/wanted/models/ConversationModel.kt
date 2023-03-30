package com.chatgptlite.wanted.models

import androidx.compose.runtime.Immutable
import com.google.firebase.Timestamp
import com.google.firebase.firestore.DocumentSnapshot
import java.util.Date
import java.util.Objects

data class ConversationModel(
    var id: String = Date().time.toString(),
    var title: String = "",
    var createdAt: Date = Date(),
)