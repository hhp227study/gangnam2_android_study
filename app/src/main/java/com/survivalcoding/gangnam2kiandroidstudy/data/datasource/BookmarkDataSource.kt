package com.survivalcoding.gangnam2kiandroidstudy.data.datasource

import kotlinx.coroutines.flow.Flow

interface BookmarkDataSource {
    fun observeBookmarks(uid: String): Flow<List<Int>>
    suspend fun addBookmark(uid: String, recipeId: Int)
    suspend fun removeBookmark(uid: String, recipeId: Int)
}