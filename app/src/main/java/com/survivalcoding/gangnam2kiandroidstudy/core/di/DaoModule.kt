package com.survivalcoding.gangnam2kiandroidstudy.core.di

import com.survivalcoding.gangnam2kiandroidstudy.data.datasource.BookmarkDatabase
import org.koin.dsl.module

val daoModule = module {
    single { get<BookmarkDatabase>().bookmarkDao() }
}