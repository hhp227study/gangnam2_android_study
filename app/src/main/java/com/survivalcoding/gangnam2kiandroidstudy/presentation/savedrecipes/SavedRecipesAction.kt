package com.survivalcoding.gangnam2kiandroidstudy.presentation.savedrecipes

import com.survivalcoding.gangnam2kiandroidstudy.domain.model.Recipe

sealed interface SavedRecipesAction {
    data class ToggleBookmark(val recipe: Recipe) : SavedRecipesAction
    data class RecipeClick(val id: Int) : SavedRecipesAction
    data object ReachedBottom : SavedRecipesAction
}