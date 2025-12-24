package com.survivalcoding.gangnam2kiandroidstudy.data.datasource

import com.survivalcoding.gangnam2kiandroidstudy.data.datasource.dto.RecipesResponse
import com.survivalcoding.gangnam2kiandroidstudy.domain.model.Recipe
import kotlinx.serialization.json.Json

class MockRecipeDataSource(
    private val jsonString: String
) : RecipeDataSource {
    override fun getRecipes(): List<Recipe> {
        val response = Json.Default.decodeFromString<RecipesResponse>(jsonString)
        return response.recipes
    }
}