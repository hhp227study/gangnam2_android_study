package com.survivalcoding.gangnam2kiandroidstudy.core.di

import androidx.room.Room
import com.survivalcoding.gangnam2kiandroidstudy.data.datasource.BookmarkDatabase
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val databaseModule = module {
    single {
        Room.databaseBuilder(
            androidContext(),
            BookmarkDatabase::class.java,
            "bookmark.db"
        ).build()
    }
}