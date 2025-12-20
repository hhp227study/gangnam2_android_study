package com.survivalcoding.gangnam2kiandroidstudy.presentation.savedrecipes

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import org.koin.androidx.compose.koinViewModel

@Composable
fun SavedRecipesRoot(
    viewModel: SavedRecipesViewModel = koinViewModel(),
    onRecipeClick: (Int) -> Unit
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    SavedRecipesScreen(uiState, onRecipeClick, viewModel::onAction)
}