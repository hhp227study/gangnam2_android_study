package com.survivalcoding.gangnam2kiandroidstudy.data

import com.survivalcoding.gangnam2kiandroidstudy.data.datasource.RecipeDataSource
import com.survivalcoding.gangnam2kiandroidstudy.data.model.Recipe
import com.survivalcoding.gangnam2kiandroidstudy.data.model.RecipesResponse
import kotlinx.serialization.json.Json

class MockRecipeDataSource(
    private val jsonString: String
) : RecipeDataSource {
    override fun getRecipes(): List<Recipe> {
        val response = Json.decodeFromString<RecipesResponse>(jsonString)
        return response.recipes
    }
}