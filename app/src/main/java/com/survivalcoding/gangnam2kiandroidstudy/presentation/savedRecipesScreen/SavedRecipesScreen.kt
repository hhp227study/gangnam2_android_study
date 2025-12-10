package com.survivalcoding.gangnam2kiandroidstudy.presentation.savedRecipesScreen

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.survivalcoding.gangnam2kiandroidstudy.data.model.Recipe
import com.survivalcoding.gangnam2kiandroidstudy.data.repository.RecipeRepository
import com.survivalcoding.gangnam2kiandroidstudy.di.DependencyContainer
import com.survivalcoding.gangnam2kiandroidstudy.presentation.component.AppBar
import com.survivalcoding.gangnam2kiandroidstudy.presentation.component.RecipeCard

@Composable
fun SavedRecipesScreen(
    viewModel: SavedRecipesViewModel = viewModel(
        factory = DependencyContainer.provideSavedRecipesViewModelFactory(LocalContext.current)
    )
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    Column(modifier = Modifier.fillMaxSize().navigationBarsPadding()) {
        AppBar(title = "Saved recipes")
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            when {
                !uiState.recipes.isEmpty() -> {
                    LazyColumn(
                        contentPadding = PaddingValues(horizontal = 30.dp, vertical = 10.dp),
                        verticalArrangement = Arrangement.spacedBy(20.dp)
                    ) {
                        items(uiState.recipes) { recipe ->
                            RecipeCard(recipe)
                        }
                    }
                }
                uiState.isLoading -> {
                    CircularProgressIndicator()
                }
                uiState.message?.isNotEmpty() == true -> {
                    Text("데이터가 없습니다.")
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun SavedRecipesScreenPreview() {
    val fakeRecipeRepository = object : RecipeRepository {
        override suspend fun getAllRecipes(): Result<List<Recipe>> {
            return Result.success(emptyList())
        }
    }
    val viewModel = SavedRecipesViewModel(fakeRecipeRepository)

    SavedRecipesScreen(viewModel)
}