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

    RecipeDetailScreen(
        recipeDetailUiState = uiState,
        onAction = viewModel::onAction,
        onNavigateUp = onNavigateUp
    )
}