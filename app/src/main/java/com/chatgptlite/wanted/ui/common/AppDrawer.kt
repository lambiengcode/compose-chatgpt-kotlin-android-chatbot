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
import androidx.compose.material.icons.filled.EditNote
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment.Companion.CenterStart
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import com.chatgptlite.wanted.data.fake.fakeConversations
import com.chatgptlite.wanted.models.ConversationModel
import com.chatgptlite.wanted.ui.theme.ChatGPTLiteTheme
import com.chatgptlite.wanted.ui.theme.PrimaryColor

@Composable
fun AppDrawer(
    onProfileClicked: (String) -> Unit,
    onChatClicked: (String) ->
    Unit
) {
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
            HistoryConversations(conversations = fakeConversations)
            DividerItem(modifier = Modifier.padding(horizontal = 28.dp))
            DrawerItemHeader("Settings")
            ChatItem("Settings", Icons.Filled.Settings, false) { onChatClicked("avc") }
            ProfileItem(
                "lambiengcode (author)",
                "https://avatars.githubusercontent.com/u/60530946?v=4"
            ) { println("onClick my profile") }
        }
    }
}

@Composable
private fun DrawerHeader() {
    val paddingSizeModifier = Modifier
        .padding(start = 16.dp, top = 16.dp, bottom = 16.dp)
        .background(
            Color.Transparent,
            shape = RoundedCornerShape(6.dp)
        )
        .size(34.dp)

    Row(modifier = Modifier.padding(16.dp), verticalAlignment = CenterVertically) {
        Image(
            painter = rememberAsyncImagePainter("https://res.cloudinary.com/apideck/image/upload/v1672442492/marketplaces/ckhg56iu1mkpc0b66vj7fsj3o/listings/-4-ans_frontend_assets.images.poe.app_icon.png-26-8aa0a2e5f237894d_tbragv.png"),
            modifier = paddingSizeModifier.then(Modifier.clip(CircleShape)),
            contentScale = ContentScale.Crop,
            contentDescription = null
        )
        Column(modifier = Modifier.padding(horizontal = 12.dp)) {
            Text(
                "ChatGPT Lite",
                fontSize = 15.sp,
                fontWeight = FontWeight.Bold,
                color = PrimaryColor
            )
            Text(
                "Powered by OpenAI",
                fontSize = 11.sp,
                fontWeight = FontWeight.Medium,
                color = Color.White,
            )
        }
    }
}

@Composable
private fun ColumnScope.HistoryConversations(conversations: List<ConversationModel>) {
    LazyColumn(
        Modifier
            .fillMaxWidth()
            .weight(1f, false)
            .padding(horizontal = 16.dp),
    ) {
        items(conversations.size) { index ->
            ChatItem(
                text = conversations[index].title,
                Icons.Filled.EditNote,
                selected = index == 0,
                onChatClicked = {},
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
            modifier = Modifier.padding(start = 12.dp)
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

@Composable
@Preview
fun DrawerPreview() {
    ChatGPTLiteTheme {
        Surface {
            Column {
                AppDrawer({}, {})
            }
        }
    }
}

@Composable
@Preview
fun DrawerPreviewDark() {
    ChatGPTLiteTheme(darkTheme = true) {
        Surface {
            Column {
                AppDrawer({}, {})
            }
        }
    }
}