package com.survivalcoding.gangnam2kiandroidstudy.core.di

import androidx.lifecycle.SavedStateHandle
import com.survivalcoding.gangnam2kiandroidstudy.data.repository.FakeRecipeRepositoryImpl
import com.survivalcoding.gangnam2kiandroidstudy.domain.repository.RecipeRepository
import com.survivalcoding.gangnam2kiandroidstudy.domain.usecase.GetAllRecipesUseCase
import com.survivalcoding.gangnam2kiandroidstudy.domain.usecase.SearchRecipeByKeywordUseCase
import com.survivalcoding.gangnam2kiandroidstudy.presentation.recipedetail.RecipeDetailViewModel
import com.survivalcoding.gangnam2kiandroidstudy.presentation.search.SearchViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val testModule = module {
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
    viewModel { (recipeId: Int) ->
        val savedStateHandle = get<SavedStateHandle>()
        savedStateHandle["recipeId"] = recipeId

        RecipeDetailViewModel(
            getRecipeDetailsUseCase = get(),
            savedStateHandle = savedStateHandle
        )
    }
}