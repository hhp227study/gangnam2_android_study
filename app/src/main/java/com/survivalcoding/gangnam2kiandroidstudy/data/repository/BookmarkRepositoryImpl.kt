package com.survivalcoding.gangnam2kiandroidstudy.data.repository

import com.survivalcoding.gangnam2kiandroidstudy.data.datasource.BookmarkDataSource
import com.survivalcoding.gangnam2kiandroidstudy.domain.repository.BookmarkRepository
import kotlinx.coroutines.flow.Flow

class BookmarkRepositoryImpl(
    private val bookmarkDataSource: BookmarkDataSource
) : BookmarkRepository {
    override fun getBookmarkedRecipeIds(uid: String): Flow<List<Int>> {
        return bookmarkDataSource.observeBookmarks(uid)
    }

    override suspend fun toggleBookmark(uid: String, recipeId: Int, isBookmarked: Boolean): Boolean {
        return runCatching {
            if (isBookmarked) {
                bookmarkDataSource.removeBookmark(uid, recipeId)
            } else {
                bookmarkDataSource.addBookmark(uid, recipeId)
            }
        }.isSuccess
    }
}