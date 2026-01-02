package com.survivalcoding.gangnam2kiandroidstudy.domain.usecase

import com.survivalcoding.gangnam2kiandroidstudy.core.util.suspendRunCatching
import com.survivalcoding.gangnam2kiandroidstudy.domain.model.Recipe
import com.survivalcoding.gangnam2kiandroidstudy.domain.repository.AuthRepository
import com.survivalcoding.gangnam2kiandroidstudy.domain.repository.BookmarkRepository

class ToggleBookmarkUseCase(
    private val bookmarkRepository: BookmarkRepository,
    private val authRepository: AuthRepository
) {
    suspend operator fun invoke(recipe: Recipe): Result<Boolean> {
        return suspendRunCatching {
            val user = authRepository.getCurrentUser() ?: return@suspendRunCatching false

            bookmarkRepository.toggleBookmark(user.uid, recipe.id, recipe.isBookmarked)
        }
    }
}