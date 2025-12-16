package com.survivalcoding.gangnam2kiandroidstudy.data.datasource.local

import com.survivalcoding.gangnam2kiandroidstudy.data.datasource.AppAssetManager
import com.survivalcoding.gangnam2kiandroidstudy.data.datasource.IngredientDataSource
import com.survivalcoding.gangnam2kiandroidstudy.domain.model.IngredientEntity
import com.survivalcoding.gangnam2kiandroidstudy.data.datasource.dto.IngredientsResponse
import com.survivalcoding.gangnam2kiandroidstudy.domain.model.RecipeIngredientEntity
import com.survivalcoding.gangnam2kiandroidstudy.data.datasource.dto.RecipesIngredientsResponse
import kotlinx.serialization.json.Json

class IngredientDataSourceImpl private constructor(
    private val appAssetManager: AppAssetManager
) : IngredientDataSource {
    val recipesIngredientsJsonString = appAssetManager.open("recipesIngredients.json").use { input ->
        input.readBytes().toString(Charsets.UTF_8)
    }

    val ingredientsJsonString = appAssetManager.open("ingredients.json").use { input ->
        input.readBytes().toString(Charsets.UTF_8)
    }

    override fun getRecipeIngredientsByRecipeId(recipeId: Int): List<RecipeIngredientEntity> {
        val response = Json.Default.decodeFromString<RecipesIngredientsResponse>(recipesIngredientsJsonString)
        return response.recipesIngredients
            .filter { it.recipeId == recipeId }
    }

    override fun getAllIngredients(): List<IngredientEntity> {
        val response = Json.Default.decodeFromString<IngredientsResponse>(ingredientsJsonString)
        return response.ingredients
    }

    companion object {
        @Volatile private var instance: IngredientDataSource? = null

        fun getInstance(appAssetManager: AppAssetManager) =
            instance ?: synchronized(this) {
                instance ?: IngredientDataSourceImpl(appAssetManager).also { instance = it }
            }
    }
}