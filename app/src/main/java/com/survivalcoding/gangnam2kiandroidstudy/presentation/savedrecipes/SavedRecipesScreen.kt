package com.survivalcoding.gangnam2kiandroidstudy.presentation.savedrecipes

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.survivalcoding.gangnam2kiandroidstudy.presentation.component.AppBar
import com.survivalcoding.gangnam2kiandroidstudy.presentation.component.LinearRecipeCard

@Composable
fun SavedRecipesScreen(
    uiState: SavedRecipesUiState,
    listState: LazyListState,
    onItemClick: (Int) -> Unit,
    onAction: (SavedRecipesAction) -> Unit,
) {
    Column(modifier = Modifier.fillMaxSize().navigationBarsPadding()) {
        AppBar(title = "Saved recipes")
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            when {
                !uiState.recipes.isEmpty() -> {
                    LazyColumn(
                        modifier = Modifier.fillMaxSize(),
                        state = listState,
                        contentPadding = PaddingValues(top = 10.dp, bottom = 45.dp, start = 30.dp, end = 30.dp),
                        verticalArrangement = Arrangement.spacedBy(20.dp)
                    ) {
                        items(uiState.recipes) { recipe ->
                            LinearRecipeCard(
                                recipe,
                                onClick = { onItemClick(recipe.id) },
                                onBookmarkClick = { onAction(SavedRecipesAction.ToggleBookmark(recipe)) }
                            )
                        }
                    }
                }
                uiState.isLoading -> {
                    CircularProgressIndicator()
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun SavedRecipesScreenPreview() {
    SavedRecipesScreen(SavedRecipesUiState(), LazyListState(), fun(_) = Unit, fun(_) = Unit)
}