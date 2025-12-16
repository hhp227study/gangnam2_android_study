package com.survivalcoding.gangnam2kiandroidstudy.data.datasource.dto

import com.survivalcoding.gangnam2kiandroidstudy.domain.model.Chef
import com.survivalcoding.gangnam2kiandroidstudy.domain.model.IngredientEntity
import com.survivalcoding.gangnam2kiandroidstudy.domain.model.Procedure
import com.survivalcoding.gangnam2kiandroidstudy.domain.model.Recipe
import com.survivalcoding.gangnam2kiandroidstudy.domain.model.RecipeIngredientEntity
import kotlinx.serialization.Serializable

@Serializable
data class RecipesResponse(val recipes: List<Recipe>)

@Serializable
data class RecipesIngredientsResponse(val recipesIngredients: List<RecipeIngredientEntity>)

@Serializable
data class IngredientsResponse(val ingredients: List<IngredientEntity>)

@Serializable
data class ProceduresResponse(val procedures: List<Procedure>)

@Serializable
data class ProfilesResponse(val profiles: List<Chef>)