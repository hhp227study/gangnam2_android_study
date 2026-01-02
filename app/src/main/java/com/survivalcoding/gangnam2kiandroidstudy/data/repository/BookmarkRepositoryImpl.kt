package com.survivalcoding.gangnam2kiandroidstudy.data.repository

import com.survivalcoding.gangnam2kiandroidstudy.data.datasource.BookmarkDao
import com.survivalcoding.gangnam2kiandroidstudy.data.datasource.local.entity.BookmarkEntity
import com.survivalcoding.gangnam2kiandroidstudy.domain.repository.BookmarkRepository
import kotlinx.coroutines.flow.Flow

class BookmarkRepositoryImpl(
    private val bookmarkDao: BookmarkDao
) : BookmarkRepository {
    override fun getBookmarkedRecipeIds(): Flow<List<Int>> {
        return bookmarkDao.getBookmarkedRecipeIds()
    }

    override suspend fun toggleBookmark(recipeId: Int, isBookmarked: Boolean): Boolean {
        return if (!isBookmarked) {
            bookmarkDao.insert(BookmarkEntity(recipeId)) > 0
        } else {
            bookmarkDao.delete(recipeId) > 0
        }
    }
}