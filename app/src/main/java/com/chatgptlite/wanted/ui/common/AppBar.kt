package com.chatgptlite.wanted.ui.common

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import com.chatgptlite.wanted.constants.urlToAvatarGPT
import com.chatgptlite.wanted.ui.conversations.ui.theme.ChatGPTLiteTheme
import com.chatgptlite.wanted.ui.theme.BackGroundColor

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppBar(onClickMenu: () -> Unit) {
    ChatGPTLiteTheme() {
        Surface(
            shadowElevation = 4.dp,
            tonalElevation = 0.dp,
        ) {
            CenterAlignedTopAppBar(
                title = {
                    val paddingSizeModifier = Modifier
                        .padding(start = 16.dp, top = 16.dp, bottom = 16.dp)
                        .size(32.dp)
                    Box {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Image(
                                painter = rememberAsyncImagePainter(urlToAvatarGPT),
                                modifier = paddingSizeModifier.then(Modifier.clip(RoundedCornerShape(6.dp))),
                                contentScale = ContentScale.Crop,
                                contentDescription = null
                            )
                            Spacer(modifier = Modifier.width(8.dp))
                            Text(
                                text = "ChatGPT",
                                textAlign = TextAlign.Center,
                                fontSize = 16.5.sp,
                                fontWeight = FontWeight.SemiBold,
                            )
                            Spacer(modifier = Modifier.width(12.dp))
                        }
                    }
                },
                navigationIcon = {
                    IconButton(
                        onClick = {
                            onClickMenu()
                        },
                    ) {
                        Icon(
                            Icons.Filled.Menu,
                            "backIcon",
                            modifier = Modifier.size(26.dp),
                            tint = MaterialTheme.colorScheme.primary,
                        )
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