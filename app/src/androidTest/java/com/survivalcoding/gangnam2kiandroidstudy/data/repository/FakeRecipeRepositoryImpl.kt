package com.survivalcoding.gangnam2kiandroidstudy.data.repository

import com.survivalcoding.gangnam2kiandroidstudy.domain.model.Recipe
import com.survivalcoding.gangnam2kiandroidstudy.domain.repository.RecipeRepository
import kotlinx.coroutines.flow.Flow

class FakeRecipeRepositoryImpl : RecipeRepository {
    private val fakeRecipes = listOf(
        Recipe(id = 1, name = "Pasta"),
        Recipe(id = 2, name = "Pizza"),
        Recipe(id = 3, name = "Burger")
    )

    override suspend fun getAllRecipes(): List<Recipe> {
        return fakeRecipes
    }

    override suspend fun getFilteredRecipes(keyword: String): List<Recipe> {
        return fakeRecipes.filter {
            it.name.contains(keyword, ignoreCase = true)
        }
    }

    override fun getFilteredRecipesByCategoryFlow(category: String): Flow<List<Recipe>> {
        TODO("Not yet implemented")
    }

    override suspend fun getRecipeById(id: Int): Recipe? =
        fakeRecipes.find { it.id == id }
}