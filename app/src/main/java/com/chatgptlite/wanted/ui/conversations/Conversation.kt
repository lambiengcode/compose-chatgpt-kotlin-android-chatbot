package com.chatgptlite.wanted.ui.conversations

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.chatgptlite.wanted.data.fake.fakeMessages
import com.chatgptlite.wanted.models.MessageModel
import com.chatgptlite.wanted.ui.conversations.components.TextInput
import com.chatgptlite.wanted.ui.conversations.ui.theme.ChatGPTLiteTheme
import com.chatgptlite.wanted.ui.theme.*

@Composable
fun Conversation() {
    ChatGPTLiteTheme(darkTheme = true) {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = BackGroundColor
        ) {
            Column {
                MessageList(messages = fakeMessages)
                TextInput()
            }
        }
    }
}

@Composable
fun ColumnScope.MessageList(messages: List<MessageModel>) {
    val listState = rememberLazyListState()
    val scrollState = rememberScrollState()

    LazyColumn(
        Modifier
            .fillMaxWidth()
            .weight(1f, false)
//            .simpleVerticalScrollbar(
//                state = listState,
//                width = 4.dp,
//                color = MaterialTheme.colorScheme.secondary,
//            )
            .padding(horizontal = 16.dp),
        reverseLayout = true,
        state = listState,
    ) {
        items(messages.size) { index ->
            Box(modifier = Modifier.padding(bottom = if (index == 0) 10.dp else 0.dp)) {
                MessageCard(message = messages[index], isLast = index == messages.size - 1)
            }
        }
    }
}

@Composable
fun MessageCard(message: MessageModel, isLast: Boolean = false) {
    Column(
        horizontalAlignment = if (message.isMe) Alignment.End else Alignment.Start,
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp)
            .padding(top = if (isLast) 80.dp else 0.dp)
    ) {
        Box(
            modifier = Modifier
                .widthIn(0.dp, 260.dp) //mention max width here
                .background(
                    if (message.isMe) BackGroundMessageHuman else BackGroundMessageGPT,
                    shape = RoundedCornerShape(12.dp)
                ),
        ) {
            Text(
                text = message.message,
                fontSize = 13.sp,
                color = if (message.isMe) ColorTextHuman else ColorTextGPT,
                modifier = Modifier.padding(horizontal = 18.dp, vertical = 12.dp),
                textAlign = TextAlign.Justify,
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