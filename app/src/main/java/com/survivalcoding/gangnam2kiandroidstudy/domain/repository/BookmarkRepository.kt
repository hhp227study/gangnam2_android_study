package com.survivalcoding.gangnam2kiandroidstudy.domain.repository

interface BookmarkRepository {
    suspend fun getBookmarkedRecipeIds(): Result<List<Int>>
    suspend fun removeBookmark(recipeId: Int): Result<Boolean>
}