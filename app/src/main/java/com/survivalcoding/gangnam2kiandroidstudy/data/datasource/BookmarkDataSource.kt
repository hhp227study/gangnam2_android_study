package com.survivalcoding.gangnam2kiandroidstudy.data.datasource

import kotlinx.coroutines.flow.Flow

interface BookmarkDataSource {
    fun getBookmarkedRecipeIds(): Flow<List<Int>>

    suspend fun addBookmark(recipeId: Int): Boolean

    suspend fun removeBookmark(recipeId: Int): Boolean
}