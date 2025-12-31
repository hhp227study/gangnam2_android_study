package com.survivalcoding.gangnam2kiandroidstudy.data.datasource.local

import com.survivalcoding.gangnam2kiandroidstudy.data.datasource.BookmarkDao
import com.survivalcoding.gangnam2kiandroidstudy.data.datasource.BookmarkDataSource
import com.survivalcoding.gangnam2kiandroidstudy.data.datasource.local.entity.BookmarkEntity
import kotlinx.coroutines.flow.Flow

class BookmarkDataSourceImpl(
    private val bookmarkDao: BookmarkDao
) : BookmarkDataSource {

    override fun getBookmarkedRecipeIds(): Flow<List<Int>> {
        return bookmarkDao.getBookmarkedRecipeIds()
    }

    override suspend fun addBookmark(recipeId: Int): Boolean {
        return bookmarkDao.insert(BookmarkEntity(recipeId)) > 0
    }

    override suspend fun removeBookmark(recipeId: Int): Boolean {
        return bookmarkDao.delete(recipeId) > 0
    }
}