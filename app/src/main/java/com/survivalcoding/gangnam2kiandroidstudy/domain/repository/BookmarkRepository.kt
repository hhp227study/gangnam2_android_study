package com.survivalcoding.gangnam2kiandroidstudy.domain.repository

import kotlinx.coroutines.flow.Flow

interface BookmarkRepository {
    fun getBookmarkedRecipeIds(): Flow<List<Int>>

    suspend fun toggleBookmark(recipeId: Int, isBookmarked: Boolean): Boolean
}