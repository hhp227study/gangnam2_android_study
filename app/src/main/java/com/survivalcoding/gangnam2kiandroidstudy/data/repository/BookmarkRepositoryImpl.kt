package com.survivalcoding.gangnam2kiandroidstudy.data.repository

import com.survivalcoding.gangnam2kiandroidstudy.data.datasource.BookmarkDataSource
import com.survivalcoding.gangnam2kiandroidstudy.domain.repository.BookmarkRepository
import kotlinx.coroutines.flow.Flow

class BookmarkRepositoryImpl(
    private val bookmarkDataSource: BookmarkDataSource
) : BookmarkRepository {
    override fun getBookmarkedRecipeIds(): Flow<List<Int>> {
        return bookmarkDataSource.getBookmarkedRecipeIds()
    }

    override suspend fun toggleBookmark(recipeId: Int, isBookmarked: Boolean): Boolean {
        return if (!isBookmarked) {
            bookmarkDataSource.addBookmark(recipeId)
        } else {
            bookmarkDataSource.removeBookmark(recipeId)
        }
    }
}