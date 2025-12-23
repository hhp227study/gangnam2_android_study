package com.survivalcoding.gangnam2kiandroidstudy.core.di

import com.survivalcoding.gangnam2kiandroidstudy.domain.usecase.*
import org.koin.dsl.module

val useCaseModule = module {
    factory {
        GetAllRecipesUseCase(get())
    }
    factory {
        GetFilteredRecipesUseCase(get())
    }
    factory {
        SearchRecipeByKeywordUseCase(get())
    }
    factory {
        GetSavedRecipesUseCase(
            recipeRepository = get(),
            bookmarkRepository = get()
        )
    }
    factory {
        RemoveBookmarkUseCase(get())
    }
    factory {
        GetRecipeDetailsUseCase(
            recipeRepository = get(),
            chefRepository = get(),
            ingredientRepository = get(),
            procedureRepository = get()
        )
    }
    factory {
        ObserveNetworkStatusUseCase(
            networkRepository = get()
        )
    }
}