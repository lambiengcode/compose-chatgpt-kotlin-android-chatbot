package com.chatgptlite.wanted.ui.conversations

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.chatgptlite.wanted.models.MessageModel
import com.chatgptlite.wanted.ui.conversations.ui.theme.ChatGPTLiteTheme
import java.util.*

@Composable
fun Conversations() {
    val messagesData: List<MessageModel> = listOf(
        MessageModel(
            isMe = true,
            message = "What's flutter",
            createdAt = Date()
        ),
        MessageModel(
            isMe = false,
            message = "Flutter is...",
            createdAt = Date()
        ),
        MessageModel(
            isMe = true,
            message = "Who is lambiengcode?",
            createdAt = Date()
        ),
        MessageModel(
            isMe = false,
            message = "I'm Kai (lambiengcode), currently working as the Technical Leader at Askany and Waodate. Computador Also, I'm a freelancer. If you have a need for a mobile application or website, contact me by email: lambiengcode@gmail.com",
            createdAt = Date()
        ),
    )

    ChatGPTLiteTheme(darkTheme = true) {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            MessageList(messages = messagesData)
        }
    }
}

@Composable
fun MessageList(messages: List<MessageModel>) {
    Column(
        Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp, vertical = 8.dp),
    ) {
        messages.forEach { message ->
            MessageCard(message = message)
        }
    }
}

@Composable
fun MessageCard(message: MessageModel) {
    Column(
        horizontalAlignment = if (message.isMe) Alignment.End else Alignment.Start,
        modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp)
    ) {
        Box(
            modifier = Modifier
                .heightIn(0.dp, 232.dp) //mention max height here
                .widthIn(0.dp, 260.dp) //mention max width here
                .background(
                    Color(0xFFE2F0E9),
                    shape = RoundedCornerShape(8.dp)
                ),
        ) {
            Text(
                text = message.message, fontSize = 13.sp, color = Color.Black,
                modifier = Modifier.padding(horizontal = 18.dp, vertical = 10.dp)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview2() {
    ChatGPTLiteTheme {
//        Greeting2("Android")
    }
}