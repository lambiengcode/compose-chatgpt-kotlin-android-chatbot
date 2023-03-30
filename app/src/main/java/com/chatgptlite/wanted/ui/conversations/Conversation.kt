package com.chatgptlite.wanted.ui.conversations

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.chatgptlite.wanted.models.MessageModel
import com.chatgptlite.wanted.ui.conversations.components.TextInput
import com.chatgptlite.wanted.ui.conversations.ui.theme.ChatGPTLiteTheme
import com.chatgptlite.wanted.ui.theme.*

@Composable
fun Conversation() {
    ChatGPTLiteTheme() {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = BackGroundColor,
        ) {
            Box(Modifier.fillMaxSize())  {
                Column(Modifier.fillMaxSize()) {
                    MessageList(modifier = Modifier.weight(1f))
                    TextInput()
                }
            }
        }
    }
}


const val ConversationTestTag = "ConversationTestTag"

@Composable
fun MessageList(
    modifier: Modifier = Modifier,
    conversationViewModel: ConversationViewModel = hiltViewModel(),
) {

    val conversationId by conversationViewModel.currentConversationState.collectAsState()
    val messagesMap by conversationViewModel.messagesState.collectAsState()
    val listState = rememberLazyListState()

    val messages: List<MessageModel> =
        if (messagesMap[conversationId] == null) listOf() else messagesMap[conversationId]!!

    Box(modifier = modifier) {
        LazyColumn(
            contentPadding =
            WindowInsets.statusBars.add(WindowInsets(top = 90.dp)).asPaddingValues(),
            modifier = Modifier
                .testTag(ConversationTestTag)
                .fillMaxSize(),
            reverseLayout = true,
            state = listState,
        ) {
            items(messages.size) { index ->
                Box(modifier = Modifier.padding(bottom = if (index == 0) 10.dp else 0.dp)) {
                    Column {
                        MessageCard(
                            message = messages[index],
                            isLast = index == messages.size - 1,
                            isHuman = true
                        )
                        MessageCard(message = messages[index])
                    }
                }
            }
        }
    }
}

@Composable
fun MessageCard(message: MessageModel, isHuman: Boolean = false, isLast: Boolean = false) {
    Column(
        horizontalAlignment = if (isHuman) Alignment.End else Alignment.Start,
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp)
            .padding(top = if (isLast) 120.dp else 0.dp)
    ) {
        Box(
            modifier = Modifier
                .widthIn(0.dp, 260.dp) //mention max width here
                .background(
                    if (isHuman) BackGroundMessageHuman else BackGroundMessageGPT,
                    shape = RoundedCornerShape(12.dp)
                ),
        ) {
            Text(
                text = if (isHuman) message.question else message.answer,
                fontSize = 13.sp,
                color = if (isHuman) ColorTextHuman else ColorTextGPT,
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