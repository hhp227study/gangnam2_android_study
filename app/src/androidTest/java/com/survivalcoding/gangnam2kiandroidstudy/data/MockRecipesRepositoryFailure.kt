package com.survivalcoding.gangnam2kiandroidstudy.data

import com.survivalcoding.gangnam2kiandroidstudy.domain.model.Recipe
import com.survivalcoding.gangnam2kiandroidstudy.domain.repository.RecipeRepository

class MockRecipesRepositoryFailure(
    private val errorMessage: String = "Failed to load recipes"
) : RecipeRepository {
    override suspend fun getAllRecipes(): Result<List<Recipe>> {
        return Result.failure(Exception(errorMessage))
    }

    override suspend fun getFilteredRecipes(keyword: String): Result<List<Recipe>> {
        return Result.failure(Exception(errorMessage))
    }
}