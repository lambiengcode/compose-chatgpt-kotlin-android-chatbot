package com.chatgptlite.wanted.ui.common

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.chatgptlite.wanted.ui.conversations.ui.theme.ChatGPTLiteTheme
import com.chatgptlite.wanted.ui.theme.BackGroundColor

@Composable
fun AppBar(onClickMenu: () -> Unit) {

    ChatGPTLiteTheme() {
        Surface(
            shadowElevation = 4.dp,
            tonalElevation = 0.dp,
        ) {
            CenterAlignedTopAppBar (
                title = {
                    Box {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Text(
                                text = "ChatGPT",
                                textAlign = TextAlign.Center,
                                fontSize = 18.sp,
                                fontWeight = FontWeight.SemiBold,
                                color = MaterialTheme.colorScheme.primary,
                            )
                        }
                    }
                },
                navigationIcon = {
                    IconButton(onClick = {
                        onClickMenu()
                    }) {
                        Icon(Icons.Filled.Menu, "backIcon", modifier = Modifier.size(26.dp))
                    }
                },
                colors = TopAppBarDefaults.smallTopAppBarColors(
                    containerColor = BackGroundColor,
                    titleContentColor = Color.White,
                ),
            )
        }
    }
}