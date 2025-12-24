package com.survivalcoding.gangnam2kiandroidstudy.presentation.savedrecipes

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import kotlinx.coroutines.flow.distinctUntilChanged
import org.koin.androidx.compose.koinViewModel

@Composable
fun SavedRecipesRoot(
    viewModel: SavedRecipesViewModel = koinViewModel(),
    onRecipeClick: (Int) -> Unit
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val listState = rememberLazyListState()
    val snackbarHostState = remember { SnackbarHostState() }

    LaunchedEffect(Unit) {
        viewModel.event.collect { event ->
            when (event) {
                is SavedRecipesEvent.NavigateToRecipe -> {
                    onRecipeClick(event.id)
                }
                is SavedRecipesEvent.ShowMessage -> {
                    snackbarHostState.showSnackbar(event.message)
                }
            }
        }
    }
    Box(modifier = Modifier.fillMaxSize()) {
        SavedRecipesScreen(uiState, listState, onRecipeClick, viewModel::onAction)
        SnackbarHost(
            hostState = snackbarHostState,
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = 45.dp)
                .navigationBarsPadding()
        )
    }
    LaunchedEffect(listState) {
        snapshotFlow {
            /*val layoutInfo = listState.layoutInfo
            val lastVisibleItemIndex =
                layoutInfo.visibleItemsInfo.lastOrNull()?.index ?: 0
            val totalItemsCount = layoutInfo.totalItemsCount

            lastVisibleItemIndex == totalItemsCount - 1*/
            !listState.canScrollForward && uiState.recipes.isNotEmpty()
        }
            .distinctUntilChanged()
            .collect { isBottom ->
                if (isBottom) {
                    viewModel.onAction(SavedRecipesAction.ReachedBottom)
                }
            }
    }
}