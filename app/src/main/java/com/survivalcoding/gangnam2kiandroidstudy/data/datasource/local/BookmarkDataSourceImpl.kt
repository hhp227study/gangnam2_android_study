package com.survivalcoding.gangnam2kiandroidstudy.data.datasource.local

import com.survivalcoding.gangnam2kiandroidstudy.data.datasource.BookmarkDataSource
import java.util.Collections

class BookmarkDataSourceImpl private constructor() : BookmarkDataSource {
    private val bookmarkedIds = Collections.synchronizedSet(
        mutableSetOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10) // 초기 북마크된 레시피
    )

    override suspend fun getBookmarkedRecipeIds(): List<Int> {
        return bookmarkedIds.toList()
    }

    override suspend fun removeBookmark(recipeId: Int): Boolean {
        return bookmarkedIds.remove(recipeId)
    }

    companion object {
        @Volatile private var instance: BookmarkDataSource? = null

        fun getInstance() =
            instance ?: synchronized(this) {
                instance ?: BookmarkDataSourceImpl().also { instance = it }
            }
    }
}