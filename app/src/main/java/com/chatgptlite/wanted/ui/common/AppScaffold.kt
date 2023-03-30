package com.chatgptlite.wanted.ui.common

import android.annotation.SuppressLint
import androidx.compose.material3.*
import androidx.compose.material3.DrawerValue.Closed
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.hilt.navigation.compose.hiltViewModel
import com.chatgptlite.wanted.ui.conversations.ConversationViewModel
import com.chatgptlite.wanted.ui.theme.BackGroundColor
import com.chatgptlite.wanted.ui.theme.ChatGPTLiteTheme
import kotlinx.coroutines.launch

//import androidx.compose.material3.ModalDrawerSheet

@SuppressLint("CoroutineCreationDuringComposition")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppScaffold (
    drawerState: DrawerState = rememberDrawerState(initialValue = Closed),
    onChatClicked: (String) -> Unit,
    onNewChatClicked: () -> Unit,
    conversationViewModel: ConversationViewModel = hiltViewModel(),
    content: @Composable () -> Unit,
) {
    val scope = rememberCoroutineScope()

    scope.launch {
        conversationViewModel.initialize()
    }


    ChatGPTLiteTheme() {
        ModalNavigationDrawer(
            drawerState = drawerState,
            drawerContent = {
                ModalDrawerSheet (drawerContainerColor = BackGroundColor) {
                    AppDrawer (
                        onChatClicked = onChatClicked,
                        onNewChatClicked = onNewChatClicked,
                    )
                }
            },
            content = content
        )
    }
}