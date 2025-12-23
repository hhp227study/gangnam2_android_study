package com.survivalcoding.gangnam2kiandroidstudy.presentation.savedrecipes

sealed interface SavedRecipesEvent {
    data class NavigateToRecipe(val id: Int) : SavedRecipesEvent
    data class ShowMessage(val message: String) : SavedRecipesEvent
}