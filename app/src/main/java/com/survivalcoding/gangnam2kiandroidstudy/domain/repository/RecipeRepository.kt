package com.survivalcoding.gangnam2kiandroidstudy.domain.repository

import com.survivalcoding.gangnam2kiandroidstudy.domain.model.Recipe

interface RecipeRepository {
    suspend fun getAllRecipes(): Result<List<Recipe>>

    suspend fun getFilteredRecipes(keyword: String): Result<List<Recipe>>

    suspend fun getFilteredRecipesByCategory(category: String): Result<List<Recipe>>

    suspend fun getRecipeById(id: Int): Result<Recipe?>
}