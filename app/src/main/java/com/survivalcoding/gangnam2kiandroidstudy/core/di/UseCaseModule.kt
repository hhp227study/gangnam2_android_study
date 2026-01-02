package com.survivalcoding.gangnam2kiandroidstudy.core.di

import com.survivalcoding.gangnam2kiandroidstudy.domain.usecase.*
import org.koin.dsl.module

val useCaseModule = module {
    factory {
        GetAllRecipesUseCase(get())
    }
    factory {
        GetFilteredRecipesUseCase(get(), get(), get())
    }
    factory {
        SearchRecipeByKeywordUseCase(get())
    }
    factory {
        GetSavedRecipesUseCase(
            recipeRepository = get(),
            bookmarkRepository = get(),
            authRepository = get()
        )
    }
    factory {
        ToggleBookmarkUseCase(get(), get())
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
    factory {
        CopyLinkUseCase(
            clipboardService = get()
        )
    }
    factory {
        SignInUseCase(
            authRepository = get()
        )
    }
    factory {
        SignUpUseCase(
            authRepository = get()
        )
    }
    factory {
        GoogleSignInUseCase(
            authRepository = get()
        )
    }
}