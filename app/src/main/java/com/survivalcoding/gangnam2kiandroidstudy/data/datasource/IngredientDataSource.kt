package com.survivalcoding.gangnam2kiandroidstudy.data.datasource

import com.survivalcoding.gangnam2kiandroidstudy.domain.model.IngredientEntity
import com.survivalcoding.gangnam2kiandroidstudy.domain.model.RecipeIngredientEntity

interface IngredientDataSource {
    fun getRecipeIngredientsByRecipeId(recipeId: Int): List<RecipeIngredientEntity>
    fun getAllIngredients(): List<IngredientEntity>
}