package com.survivalcoding.gangnam2kiandroidstudy.data.repository

import com.survivalcoding.gangnam2kiandroidstudy.core.util.suspendRunCatching
import com.survivalcoding.gangnam2kiandroidstudy.data.datasource.IngredientDataSource
import com.survivalcoding.gangnam2kiandroidstudy.domain.model.RecipeIngredient
import com.survivalcoding.gangnam2kiandroidstudy.domain.repository.IngredientRepository

class IngredientRepositoryImpl private constructor(
    private val ingredientDataSource: IngredientDataSource
) : IngredientRepository {
    override suspend fun getIngredientByRecipeId(recipeId: Int): Result<List<RecipeIngredient>> {
        return suspendRunCatching {
            val recipeIngredients = ingredientDataSource.getRecipeIngredientsByRecipeId(recipeId)
            val ingredients = ingredientDataSource.getAllIngredients()
            recipeIngredients.mapNotNull { recipeIngredient ->
                val ingredient = ingredients.find { it.id == recipeIngredient.ingredientId }
                ingredient?.let {
                    RecipeIngredient(
                        name = it.name,
                        image = it.image,
                        amount = recipeIngredient.amount
                    )
                }
            }
        }
    }

    companion object {
        @Volatile private var instance: IngredientRepository? = null

        fun getInstance(ingredientDataSource: IngredientDataSource) =
            instance ?: synchronized(this) {
                instance ?: IngredientRepositoryImpl(ingredientDataSource).also { instance = it }
            }
    }
}