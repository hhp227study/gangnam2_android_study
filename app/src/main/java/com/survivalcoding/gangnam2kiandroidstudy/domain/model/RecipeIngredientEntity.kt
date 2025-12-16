package com.survivalcoding.gangnam2kiandroidstudy.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class RecipeIngredientEntity(
    val recipeId: Int,
    val ingredientId: Int,
    val amount: Int
)