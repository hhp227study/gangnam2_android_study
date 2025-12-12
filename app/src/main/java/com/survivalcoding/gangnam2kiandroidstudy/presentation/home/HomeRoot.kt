package com.survivalcoding.gangnam2kiandroidstudy.presentation.home

import androidx.compose.runtime.*
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.survivalcoding.gangnam2kiandroidstudy.di.DependencyContainer

@Composable
fun HomeRoot(
    viewModel: HomeViewModel = viewModel(
        factory = DependencyContainer.provideHomeViewModelFactory(LocalContext.current)
    ),
    onSearchKeywordFocusChanged: (Boolean) -> Unit
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val keyboardController = LocalSoftwareKeyboardController.current

    HomeScreen(
        uiState = uiState,
        onSearchKeywordChange = viewModel::onSearchKeywordChange,
        onSelectCategory = viewModel::onSelectCategory,
        onSearchKeywordFocusChanged = {
            keyboardController?.hide()
            onSearchKeywordFocusChanged(it)
        }
    )
}