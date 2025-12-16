package com.survivalcoding.gangnam2kiandroidstudy.presentation.recipedetail

import com.survivalcoding.gangnam2kiandroidstudy.domain.model.Chef
import com.survivalcoding.gangnam2kiandroidstudy.domain.model.Procedure
import com.survivalcoding.gangnam2kiandroidstudy.domain.model.Recipe
import com.survivalcoding.gangnam2kiandroidstudy.domain.model.RecipeIngredient

data class RecipeDetailUiState(
    val isLoading: Boolean = false,
    val selectedTabPosition: Int = 0, // 0 = Ingredient, 1 = Procedure
    val recipe: Recipe = Recipe(0),
    val ingredients: List<RecipeIngredient> = emptyList(),
    val procedures: List<Procedure> = emptyList(),
    val chef: Chef? = null,
    val message: String? = null
)