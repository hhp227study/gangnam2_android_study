package com.survivalcoding.gangnam2kiandroidstudy.domain.repository

import kotlinx.coroutines.flow.Flow

interface BookmarkRepository {
    fun getBookmarkedRecipeIds(uid: String): Flow<List<Int>>

    suspend fun toggleBookmark(uid: String, recipeId: Int, isBookmarked: Boolean): Boolean
}