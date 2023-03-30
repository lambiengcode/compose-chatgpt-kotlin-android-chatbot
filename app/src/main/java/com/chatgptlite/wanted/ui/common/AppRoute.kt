package com.chatgptlite.wanted.ui.common

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

@Composable
fun AppRoute(
//    appContainer: AppContainer,
    isExpandedScreen: Boolean,
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    openDrawer: () -> Unit = {},
    startDestination: String = "/"
) {
    NavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = modifier
    ) {
        composable("/") {
//            val homeViewModel: HomeViewModel = viewModel(
//                factory = HomeViewModel.provideFactory(appContainer.postsRepository)
//            )
//            Conversation (
//
//            )
        }
    }
}