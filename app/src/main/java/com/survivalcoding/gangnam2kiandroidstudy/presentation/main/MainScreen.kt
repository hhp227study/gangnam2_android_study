package com.survivalcoding.gangnam2kiandroidstudy.presentation.main

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation3.runtime.NavBackStack
import androidx.navigation3.runtime.NavKey
import androidx.navigation3.runtime.rememberNavBackStack
import com.survivalcoding.gangnam2kiandroidstudy.core.routing.Route
import com.survivalcoding.gangnam2kiandroidstudy.presentation.component.MainNavigationBar
import com.survivalcoding.gangnam2kiandroidstudy.ui.AppColors

@Composable
fun MainScreen(
    modifier: Modifier = Modifier,
    backStack: NavBackStack<NavKey>,
    body: @Composable (modifier: Modifier) -> Unit = {},
    onFabClick: () -> Unit = {}
) {
    val currentRoute = backStack.lastOrNull()

    Box(
        modifier = modifier
            .fillMaxSize()
            .navigationBarsPadding()
    ) {
        Column(modifier.padding(bottom = 34.dp)) {
            body(Modifier.background(AppColors.primary100).padding(bottom = 100.dp))
        }
        MainNavigationBar(
            modifier = Modifier.align(Alignment.BottomCenter),
            currentRoute = currentRoute,
            onNavigate = {
                backStack.clear()
                backStack.add(it)
            },
            onFabClick = onFabClick
        )
    }
}

@Preview(showBackground = true)
@Composable
fun MainScreenPreview() {
    val backStack = rememberNavBackStack(Route.Home)
    MainScreen(backStack = backStack)
}