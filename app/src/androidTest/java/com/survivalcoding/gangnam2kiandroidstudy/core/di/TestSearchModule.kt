package com.survivalcoding.gangnam2kiandroidstudy.core.di

import com.survivalcoding.gangnam2kiandroidstudy.data.repository.FakeRecipeRepositoryImpl
import com.survivalcoding.gangnam2kiandroidstudy.domain.repository.RecipeRepository
import com.survivalcoding.gangnam2kiandroidstudy.domain.usecase.GetAllRecipesUseCase
import com.survivalcoding.gangnam2kiandroidstudy.domain.usecase.SearchRecipeByKeywordUseCase
import com.survivalcoding.gangnam2kiandroidstudy.presentation.search.SearchViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val testSearchModule = module {
    single<RecipeRepository> {
        FakeRecipeRepositoryImpl()
    }
    single {
        GetAllRecipesUseCase(get())
    }
    single {
        SearchRecipeByKeywordUseCase(get())
    }
    viewModel {
        SearchViewModel(
            getAllRecipesUseCase = get(),
            searchRecipeByKeywordUseCase = get()
        )
    }
}