package com.chatgptlite.wanted.ui.common

import androidx.compose.material3.DrawerState
import androidx.compose.material3.DrawerValue.Closed
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import com.chatgptlite.wanted.ui.theme.ChatGPTLiteTheme
//import androidx.compose.material3.ModalDrawerSheet

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppScaffold (
    drawerState: DrawerState = rememberDrawerState(initialValue = Closed),
    onProfileClicked: (String) -> Unit,
    onChatClicked: (String) -> Unit,
    content: @Composable () -> Unit
) {
    ChatGPTLiteTheme() {
        ModalNavigationDrawer(
            drawerState = drawerState,
            drawerContent = {
                AppDrawer (
                    onProfileClicked = onProfileClicked,
                    onChatClicked = onChatClicked
                )
            },
            content = content
        )
    }
}