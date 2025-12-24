package com.survivalcoding.gangnam2kiandroidstudy.domain.usecase

import com.survivalcoding.gangnam2kiandroidstudy.core.util.suspendRunCatching
import com.survivalcoding.gangnam2kiandroidstudy.domain.model.Recipe
import com.survivalcoding.gangnam2kiandroidstudy.domain.repository.BookmarkRepository
import com.survivalcoding.gangnam2kiandroidstudy.domain.repository.RecipeRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class GetSavedRecipesUseCase(
    private val recipeRepository: RecipeRepository,
    private val bookmarkRepository: BookmarkRepository
) {
    operator fun invoke(): Flow<Result<List<Recipe>>> {
        return bookmarkRepository.getBookmarkedRecipeIds()
            .map { bookmarkIds ->
                suspendRunCatching {
                    recipeRepository.getAllRecipes()
                        .filter { it.id in bookmarkIds }
                        .map { it.copy(isBookmarked = true) }
                }
            }
    }
}