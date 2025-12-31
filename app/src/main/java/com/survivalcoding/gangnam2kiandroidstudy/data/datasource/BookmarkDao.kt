package com.survivalcoding.gangnam2kiandroidstudy.data.datasource

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.survivalcoding.gangnam2kiandroidstudy.data.datasource.local.entity.BookmarkEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface BookmarkDao {
    @Query("SELECT recipeId FROM bookmarks")
    fun getBookmarkedRecipeIds(): Flow<List<Int>>

    @Insert(onConflict = OnConflictStrategy.Companion.IGNORE)
    suspend fun insert(entity: BookmarkEntity): Long

    @Query("DELETE FROM bookmarks WHERE recipeId = :recipeId")
    suspend fun delete(recipeId: Int): Int
}