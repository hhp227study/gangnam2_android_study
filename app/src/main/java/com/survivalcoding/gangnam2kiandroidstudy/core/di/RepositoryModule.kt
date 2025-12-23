package com.survivalcoding.gangnam2kiandroidstudy.core.di

import com.survivalcoding.gangnam2kiandroidstudy.data.repository.*
import com.survivalcoding.gangnam2kiandroidstudy.domain.repository.*
import org.koin.dsl.module

val repositoryModule = module {
    single<RecipeRepository> {
        RecipeRepositoryImpl(get())
    }
    single<IngredientRepository> {
        IngredientRepositoryImpl(get())
    }
    single<ProcedureRepository> {
        ProcedureRepositoryImpl(get())
    }
    single<ChefRepository> {
        ChefRepositoryImpl(get())
    }
    single<BookmarkRepository> {
        BookmarkRepositoryImpl(get())
    }
    single<NetworkRepository> {
        NetworkRepositoryImpl(get())
    }
}