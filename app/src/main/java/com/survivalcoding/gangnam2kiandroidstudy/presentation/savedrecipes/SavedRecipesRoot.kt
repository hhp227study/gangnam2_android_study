package com.survivalcoding.gangnam2kiandroidstudy.presentation.savedrecipes

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import org.koin.androidx.compose.koinViewModel

@Composable
fun SavedRecipesRoot(
    viewModel: SavedRecipesViewModel = koinViewModel(),
    onRecipeClick: (Int) -> Unit
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    LaunchedEffect(Unit) {
        viewModel.event.collect { event ->
            when (event) {
                is SavedRecipesEvent.NavigateToRecipe -> {
                    onRecipeClick(event.id)
                }
                is SavedRecipesEvent.ShowMessage -> {
                    // Snackbar / Toast 처리
                }
            }
        }
    }
    SavedRecipesScreen(uiState, onRecipeClick, viewModel::onAction)
}