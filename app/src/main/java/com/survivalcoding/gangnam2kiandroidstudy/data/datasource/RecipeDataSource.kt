package com.survivalcoding.gangnam2kiandroidstudy.data.datasource

import com.survivalcoding.gangnam2kiandroidstudy.domain.model.Recipe

interface RecipeDataSource {
    fun getRecipes(): List<Recipe>
}