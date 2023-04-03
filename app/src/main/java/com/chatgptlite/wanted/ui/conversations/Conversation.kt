package com.chatgptlite.wanted.ui.conversations

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.chatgptlite.wanted.models.MessageModel
import com.chatgptlite.wanted.ui.conversations.components.MessageCard
import com.chatgptlite.wanted.ui.conversations.components.TextInput
import com.chatgptlite.wanted.ui.conversations.ui.theme.ChatGPTLiteTheme
import com.chatgptlite.wanted.ui.theme.BackGroundColor

@Composable
fun Conversation() {
    ChatGPTLiteTheme() {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = BackGroundColor,
        ) {
            Box(Modifier.fillMaxSize()) {
                Column(Modifier.fillMaxSize()) {
                    MessageList(modifier = Modifier
                        .weight(1f)
                        .padding(horizontal = 16.dp))
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
    val listState = rememberLazyListState()

    val conversationId by conversationViewModel.currentConversationState.collectAsState()
    val messagesMap by conversationViewModel.messagesState.collectAsState()

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



@Preview(showBackground = true)
@Composable
fun DefaultPreview2() {
    ChatGPTLiteTheme {
//        Greeting2("Android")
    }
}