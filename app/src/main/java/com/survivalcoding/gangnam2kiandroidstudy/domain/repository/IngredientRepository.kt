package com.survivalcoding.gangnam2kiandroidstudy.domain.repository

import com.survivalcoding.gangnam2kiandroidstudy.domain.model.RecipeIngredient

interface IngredientRepository {
    suspend fun getIngredientByRecipeId(recipeId: Int): Result<List<RecipeIngredient>>
}