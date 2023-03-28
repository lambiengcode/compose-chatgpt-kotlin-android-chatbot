package com.chatgptlite.wanted

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.view.WindowCompat
import com.chatgptlite.wanted.ui.common.AppBar
import com.chatgptlite.wanted.ui.common.AppScaffold
import com.chatgptlite.wanted.ui.conversations.Conversations
import com.chatgptlite.wanted.ui.theme.ChatGPTLiteTheme
import kotlinx.coroutines.launch


class MainActivity : ComponentActivity() {

    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        WindowCompat.setDecorFitsSystemWindows(window, true)

        setContentView(
            ComposeView(this).apply {
                consumeWindowInsets = false
                setContent {
                    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)

                    // Intercepts back navigation when the drawer is open
                    val scope = rememberCoroutineScope()
//                    if (drawerState.isOpen) {
//                        BackPressHandler {
//                            scope.launch {
//                                drawerState.close()
//                            }
//                        }
//                    }

                    ChatGPTLiteTheme() {
                        Surface(
                            color = MaterialTheme.colorScheme.background,
                        ) {
                            AppScaffold(
                                drawerState = drawerState,
                                onChatClicked = {
                                    scope.launch {
                                        drawerState.close()
                                    }
                                },
                                onProfileClicked = {
                                }
                            ) {
                                Scaffold(
                                    modifier = Modifier.safeContentPadding(),
                                    topBar = {
                                        AppBar(
                                            onClickMenu = {
                                                scope.launch {
                                                    drawerState.open()
                                                }
                                            }
                                        )
                                    },
                                    content = {
                                        Conversations()
                                    },
                                )
                            }
                        }
                    }
                }
            }
        )
    }
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!", modifier = Modifier.pointerInput(Unit) {
        detectTapGestures(
            onPress = { offset -> },
            onDoubleTap = { offset -> },
            onLongPress = { offset -> },
            onTap = { offset -> }
        )
        // or other similar...
    })
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ChatGPTLiteTheme {
    }
}