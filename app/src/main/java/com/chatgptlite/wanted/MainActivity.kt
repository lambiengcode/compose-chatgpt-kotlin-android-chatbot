package com.chatgptlite.wanted

import android.os.Bundle
import android.widget.ListView
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.view.WindowCompat
import androidx.navigation.findNavController
import com.chatgptlite.wanted.ui.common.AppDrawer
import com.chatgptlite.wanted.ui.common.AppScaffold
import com.chatgptlite.wanted.ui.conversations.Conversations
import com.chatgptlite.wanted.ui.theme.ChatGPTLiteTheme
import androidx.compose.ui.viewinterop.AndroidView
import androidx.compose.ui.viewinterop.AndroidViewBinding
import androidx.core.content.ContextCompat
import com.chatgptlite.wanted.ui.theme.BackGroundColor
import com.google.accompanist.systemuicontroller.rememberSystemUiController

class MainActivity : ComponentActivity() {

    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        WindowCompat.setDecorFitsSystemWindows(window, true)


        setContentView(
            ComposeView(this).apply {
                consumeWindowInsets = false
                setContent {
                    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
                    val systemUiController = rememberSystemUiController()

                    SideEffect {
                        systemUiController.setStatusBarColor(
                            color = Color.Red,
                        )
                    }

                    AppScaffold(
                        drawerState = drawerState,
                        onChatClicked = {
//                            findNavController().popBackStack(R.id.main, false)
//                            scope.launch {
//                                drawerState.close()
//                            }
                        },
                        onProfileClicked = {
//                            val bundle = bundleOf("userId" to it)
//                            findNavController().navigate(R.id.nav_profile, bundle)
//                            scope.launch {
//                                drawerState.close()
//                            }
                        }
                    ) {
                        Conversations()
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
            onTap = { offset ->  }
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