package com.survivalcoding.gangnam2kiandroidstudy.presentation.savedrecipes

sealed interface SavedRecipesAction {
    data class RemoveBookmark(val id: Int) : SavedRecipesAction
    data class RecipeClick(val id: Int) : SavedRecipesAction
}