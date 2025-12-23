package com.survivalcoding.gangnam2kiandroidstudy.presentation.recipedetail

import androidx.compose.runtime.*
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import org.koin.androidx.compose.koinViewModel

@Composable
fun RecipeDetailRoot(
    viewModel: RecipeDetailViewModel = koinViewModel(),
    onNavigateUp: () -> Unit
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    LaunchedEffect(Unit) {
        viewModel.event.collect { event ->
            when (event) {
                RecipeDetailEvent.NavigateUp -> onNavigateUp()
                is RecipeDetailEvent.ShowMessage -> {
                    // Snackbar / Toast
                }
                is RecipeDetailEvent.FollowCompleted -> {
                    // Snackbar("Followed!")
                }
            }
        }
    }
    RecipeDetailScreen(
        recipeDetailUiState = uiState,
        onAction = viewModel::onAction
    )
}