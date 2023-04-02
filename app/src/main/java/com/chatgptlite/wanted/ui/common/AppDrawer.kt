package com.chatgptlite.wanted.ui.common

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Message
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.WbSunny
import androidx.compose.material.icons.outlined.AddComment
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment.Companion.CenterStart
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.rememberAsyncImagePainter
import com.chatgptlite.wanted.constants.urlToGithub
import com.chatgptlite.wanted.constants.urlToImageAppIcon
import com.chatgptlite.wanted.constants.urlToImageAuthor
import com.chatgptlite.wanted.helpers.UrlLauncher
import com.chatgptlite.wanted.models.ConversationModel
import com.chatgptlite.wanted.ui.conversations.ConversationViewModel
import com.chatgptlite.wanted.ui.theme.ChatGPTLiteTheme
import kotlinx.coroutines.launch

@Composable
fun AppDrawer(
    onChatClicked: (String) -> Unit,
    onNewChatClicked: () -> Unit,
    conversationViewModel: ConversationViewModel = hiltViewModel(),
) {
    val context = LocalContext.current

    ChatGPTLiteTheme() {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background)
        ) {
            Spacer(Modifier.windowInsetsTopHeight(WindowInsets.statusBars))
            DrawerHeader()
            DividerItem()
            DrawerItemHeader("Chats")
            ChatItem("New Chat", Icons.Outlined.AddComment, false) {
                onNewChatClicked()
                conversationViewModel.newConversation()
            }
            HistoryConversations(onChatClicked)
            DividerItem(modifier = Modifier.padding(horizontal = 28.dp))
            DrawerItemHeader("Settings")
            ChatItem("Settings", Icons.Filled.Settings, false) { onChatClicked("Settings") }
            ProfileItem(
                "lambiengcode (author)",
                urlToImageAuthor,
            ) {
                UrlLauncher().openUrl(context = context, urlToGithub)
            }
        }
    }
}

@Composable
private fun DrawerHeader() {
    val paddingSizeModifier = Modifier
        .padding(start = 16.dp, top = 16.dp, bottom = 16.dp)
        .size(34.dp)

    Row(verticalAlignment = CenterVertically, horizontalArrangement = Arrangement.SpaceBetween) {
        Row(
            modifier = Modifier
                .padding(16.dp)
                .weight(1f), verticalAlignment = CenterVertically
        ) {
            Image(
                painter = rememberAsyncImagePainter(urlToImageAppIcon),
                modifier = paddingSizeModifier.then(Modifier.clip(RoundedCornerShape(6.dp))),
                contentScale = ContentScale.Crop,
                contentDescription = null
            )
            Column(modifier = Modifier.padding(horizontal = 12.dp)) {
                Text(
                    "ChatGPT Lite",
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.secondary,
                )
                Text(
                    "Powered by OpenAI",
                    fontSize = 11.sp,
                    fontWeight = FontWeight.Normal,
                    color = Color.White,
                )
            }
        }

        IconButton(
            onClick = {
            },
        ) {
            Icon(
                Icons.Filled.WbSunny,
                "backIcon",
                modifier = Modifier.size(26.dp),
                tint = MaterialTheme.colorScheme.primary,
            )
        }
    }
}

@Composable
private fun ColumnScope.HistoryConversations(
    onChatClicked: (String) -> Unit,
    conversationViewModel: ConversationViewModel = hiltViewModel(),
) {
    val scope = rememberCoroutineScope()
    val conversationId: String by conversationViewModel.currentConversationState.collectAsState()
    val conversations: List<ConversationModel> by conversationViewModel.conversationsState.collectAsState()

    LazyColumn(
        Modifier
            .fillMaxWidth()
            .weight(1f, false),
    ) {
        items(conversations.size) { index ->
            ChatItem(
                text = conversations[index].title,
                Icons.Filled.Message,
                selected = conversations[index].id == conversationId,
                onChatClicked = {
                    onChatClicked(conversations[index].id)

                    scope.launch {
                        conversationViewModel.onConversation(conversations[index])
                    }
                },
            )
        }
    }
}

@Composable
private fun DrawerItemHeader(text: String) {
    Box(
        modifier = Modifier
            .heightIn(min = 52.dp)
            .padding(horizontal = 28.dp),
        contentAlignment = CenterStart
    ) {
        Text(
            text,
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
    }
}

@Composable
private fun ChatItem(
    text: String,
    icon: ImageVector = Icons.Filled.Edit,
    selected: Boolean,
    onChatClicked: () -> Unit
) {
    val background = if (selected) {
        Modifier.background(MaterialTheme.colorScheme.primaryContainer)
    } else {
        Modifier
    }
    Row(
        modifier = Modifier
            .height(56.dp)
            .fillMaxWidth()
            .padding(horizontal = 12.dp)
            .clip(CircleShape)
            .then(background)
            .clickable(onClick = onChatClicked),
        verticalAlignment = CenterVertically
    ) {
        val iconTint = if (selected) {
            MaterialTheme.colorScheme.primary
        } else {
            MaterialTheme.colorScheme.onSurfaceVariant
        }
        Icon(
            icon,
            tint = iconTint,
            modifier = Modifier
                .padding(start = 16.dp, top = 16.dp, bottom = 16.dp)
                .size(25.dp),
            contentDescription = null,
        )
        Text(
            text,
            style = MaterialTheme.typography.bodyMedium,
            color = if (selected) {
                MaterialTheme.colorScheme.primary
            } else {
                MaterialTheme.colorScheme.onSurface
            },
            modifier = Modifier.padding(start = 12.dp),
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
        )
    }
}

@Composable
private fun ProfileItem(text: String, urlToImage: String?, onProfileClicked: () -> Unit) {
    Row(
        modifier = Modifier
            .height(56.dp)
            .fillMaxWidth()
            .padding(horizontal = 12.dp)
            .clip(CircleShape)
            .clickable(onClick = onProfileClicked),
        verticalAlignment = CenterVertically
    ) {
        val paddingSizeModifier = Modifier
            .padding(start = 16.dp, top = 16.dp, bottom = 16.dp)
            .size(24.dp)
        if (urlToImage != null) {
            Image(
                painter = rememberAsyncImagePainter(urlToImage),
                modifier = paddingSizeModifier.then(Modifier.clip(CircleShape)),
                contentScale = ContentScale.Crop,
                contentDescription = null
            )
        } else {
            Spacer(modifier = paddingSizeModifier)
        }
        Text(
            text,
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurface,
            modifier = Modifier.padding(start = 12.dp)
        )
    }
}

@Composable
fun DividerItem(modifier: Modifier = Modifier) {
    Divider(
        modifier = modifier,
        color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.12f)
    )
}