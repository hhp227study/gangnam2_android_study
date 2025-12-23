package com.survivalcoding.gangnam2kiandroidstudy.presentation.home

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import org.koin.androidx.compose.koinViewModel

@Composable
fun HomeRoot(
    viewModel: HomeViewModel = koinViewModel(),
    onSearchKeywordFocusChanged: (Boolean) -> Unit
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val keyboardController = LocalSoftwareKeyboardController.current

    LaunchedEffect(Unit) {
        viewModel.event.collect { event ->
            when (event) {
                is HomeEvent.SearchFocusChanged -> {
                    keyboardController?.hide()
                    onSearchKeywordFocusChanged(event.focused)
                }
                is HomeEvent.ShowMessage -> {
                    // Snackbar / Toast 처리 가능
                }
            }
        }
    }
    HomeScreen(
        uiState = uiState,
        onAction = viewModel::onAction
    )
}