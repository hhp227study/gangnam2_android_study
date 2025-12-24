package com.survivalcoding.gangnam2kiandroidstudy.data.repository

import com.survivalcoding.gangnam2kiandroidstudy.domain.model.Recipe
import com.survivalcoding.gangnam2kiandroidstudy.domain.repository.RecipeRepository

class MockRecipesRepositoryFailure(
    private val errorMessage: String = "Failed to load recipes"
) : RecipeRepository {
    override suspend fun getAllRecipes(): List<Recipe> {
        return throw Exception(errorMessage)
    }

    override suspend fun getFilteredRecipes(keyword: String): List<Recipe> {
        return throw Exception(errorMessage)
    }

    override suspend fun getFilteredRecipesByCategory(category: String): List<Recipe> {
        return throw Exception(errorMessage)
    }

    override suspend fun getRecipeById(id: Int): Recipe? {
        return throw Exception(errorMessage)
    }
}