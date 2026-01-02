package com.survivalcoding.gangnam2kiandroidstudy.core.di

import com.survivalcoding.gangnam2kiandroidstudy.data.datasource.AppAssetManager
import com.survivalcoding.gangnam2kiandroidstudy.data.datasource.ChefDataSource
import com.survivalcoding.gangnam2kiandroidstudy.data.datasource.IngredientDataSource
import com.survivalcoding.gangnam2kiandroidstudy.data.datasource.NetworkDataSource
import com.survivalcoding.gangnam2kiandroidstudy.data.datasource.ProcedureDataSource
import com.survivalcoding.gangnam2kiandroidstudy.data.datasource.RecipeDataSource
import com.survivalcoding.gangnam2kiandroidstudy.data.datasource.local.AppAssetManagerImpl
import com.survivalcoding.gangnam2kiandroidstudy.data.datasource.local.ChefDataSourceImpl
import com.survivalcoding.gangnam2kiandroidstudy.data.datasource.local.IngredientDataSourceImpl
import com.survivalcoding.gangnam2kiandroidstudy.data.datasource.local.ProcedureDataSourceImpl
import com.survivalcoding.gangnam2kiandroidstudy.data.datasource.local.RecipeDataSourceImpl
import com.survivalcoding.gangnam2kiandroidstudy.data.datasource.remote.NetworkDataSourceImpl
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val dataModule = module {
    single<AppAssetManager> {
        AppAssetManagerImpl(androidContext().assets)
    }
    single<RecipeDataSource> {
        RecipeDataSourceImpl(get())
    }
    single<IngredientDataSource> {
        IngredientDataSourceImpl(get())
    }
    single<ProcedureDataSource> {
        ProcedureDataSourceImpl(get())
    }
    single<ChefDataSource> {
        ChefDataSourceImpl(get())
    }
    single<NetworkDataSource> {
        NetworkDataSourceImpl(get())
    }
}