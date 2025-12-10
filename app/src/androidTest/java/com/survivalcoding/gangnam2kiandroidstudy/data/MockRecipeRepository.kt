package com.survivalcoding.gangnam2kiandroidstudy.data

import com.survivalcoding.gangnam2kiandroidstudy.data.datasource.RecipeDataSource
import com.survivalcoding.gangnam2kiandroidstudy.data.model.Recipe
import com.survivalcoding.gangnam2kiandroidstudy.data.repository.RecipeRepository

class MockRecipeRepository(
    private val dataSource: RecipeDataSource,
    var shouldFailAllRecipes: Boolean = false,
    var shouldFailFilteredRecipes: Boolean = false,
    var failureMessage: String = "error"
) : RecipeRepository {

    override suspend fun getAllRecipes(): Result<List<Recipe>> {
        return if (shouldFailAllRecipes) {
            Result.failure(Exception(failureMessage))
        } else {
            Result.success(dataSource.getRecipes())
        }
    }

    override suspend fun getFilteredRecipes(keyword: String): Result<List<Recipe>> {
        return if (shouldFailFilteredRecipes) {
            Result.failure(Exception(failureMessage))
        } else {
            val keywordLower = keyword.lowercase()

            val filtered = dataSource.getRecipes().filter {
                it.name.lowercase().contains(keywordLower)
                        || it.chef.lowercase().contains(keywordLower)
            }

            Result.success(filtered)
        }
    }
}