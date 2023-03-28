package com.chatgptlite.wanted

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.BackHandler
import androidx.activity.viewModels
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.view.WindowCompat
import com.chatgptlite.wanted.ui.common.AppBar
import com.chatgptlite.wanted.ui.common.AppScaffold
import com.chatgptlite.wanted.ui.conversations.Conversation
import com.chatgptlite.wanted.ui.theme.ChatGPTLiteTheme
import kotlinx.coroutines.launch


class MainActivity : ComponentActivity() {
    private val viewModel: MainViewModel by viewModels()

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
                    viewModel
                    val drawerOpen by viewModel.drawerShouldBeOpened.collectAsState()

                    if (drawerOpen) {
                        // Open drawer and reset state in VM.
                        LaunchedEffect(Unit) {
                            // wrap in try-finally to handle interruption whiles opening drawer
                            try {
                                drawerState.open()
                            } finally {
                                viewModel.resetOpenDrawerAction()
                            }
                        }
                    }

                    // Intercepts back navigation when the drawer is open
                    val scope = rememberCoroutineScope()
                    if (drawerState.isOpen) {
                        BackHandler {
                            scope.launch {
                                drawerState.close()
                            }
                        }
                    }

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

                                    println(it)
                                },
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
                                        Conversation()
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

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ChatGPTLiteTheme {
    }
}