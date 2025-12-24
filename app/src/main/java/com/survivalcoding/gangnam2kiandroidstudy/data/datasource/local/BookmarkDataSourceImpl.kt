package com.survivalcoding.gangnam2kiandroidstudy.data.datasource.local

import com.survivalcoding.gangnam2kiandroidstudy.data.datasource.BookmarkDataSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class BookmarkDataSourceImpl : BookmarkDataSource {
    private val bookmarkedIds = MutableStateFlow(listOf(1)) // 초기 북마크된 레시피

    override fun getBookmarkedRecipeIds(): Flow<List<Int>> {
        return bookmarkedIds.asStateFlow()
    }

    override suspend fun addBookmark(recipeId: Int): Boolean {
        bookmarkedIds.update { it + listOf(recipeId) }
        return true
    }

    override suspend fun removeBookmark(recipeId: Int): Boolean {
        bookmarkedIds.update { it - listOf(recipeId) }
        return true
    }
}