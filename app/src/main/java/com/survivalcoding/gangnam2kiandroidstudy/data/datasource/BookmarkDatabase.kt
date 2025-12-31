package com.survivalcoding.gangnam2kiandroidstudy.data.datasource

import androidx.room.Database
import androidx.room.RoomDatabase
import com.survivalcoding.gangnam2kiandroidstudy.data.datasource.local.entity.BookmarkEntity

@Database(
    entities = [BookmarkEntity::class],
    version = 1,
    exportSchema = false
)
abstract class BookmarkDatabase : RoomDatabase() {
    abstract fun bookmarkDao(): BookmarkDao
}