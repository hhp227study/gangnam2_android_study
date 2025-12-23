package com.survivalcoding.gangnam2kiandroidstudy.presentation.recipedetail

sealed interface RecipeDetailEvent {
    object NavigateUp : RecipeDetailEvent
    data class ShowMessage(val message: String) : RecipeDetailEvent
    data class FollowCompleted(val chefId: Int) : RecipeDetailEvent
}