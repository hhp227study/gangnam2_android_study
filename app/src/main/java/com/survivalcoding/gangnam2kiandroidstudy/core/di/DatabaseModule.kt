package com.survivalcoding.gangnam2kiandroidstudy.core.di

import androidx.room.Room
import com.google.firebase.firestore.FirebaseFirestore
import com.survivalcoding.gangnam2kiandroidstudy.BuildConfig
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
    single {
        val firestore = FirebaseFirestore.getInstance()

        if (BuildConfig.FLAVOR != "prod") {
            firestore.useEmulator("10.0.2.2", 8080)
        }
        firestore
    }
}