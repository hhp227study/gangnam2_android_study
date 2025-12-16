package com.survivalcoding.gangnam2kiandroidstudy.di

import android.content.Context
import androidx.lifecycle.createSavedStateHandle
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.survivalcoding.gangnam2kiandroidstudy.core.routing.Route
import com.survivalcoding.gangnam2kiandroidstudy.data.datasource.local.AppAssetManagerImpl
import com.survivalcoding.gangnam2kiandroidstudy.data.datasource.BookmarkDataSource
import com.survivalcoding.gangnam2kiandroidstudy.data.datasource.local.BookmarkDataSourceImpl
import com.survivalcoding.gangnam2kiandroidstudy.data.datasource.ChefDataSource
import com.survivalcoding.gangnam2kiandroidstudy.data.datasource.local.ChefDataSourceImpl
import com.survivalcoding.gangnam2kiandroidstudy.data.datasource.IngredientDataSource
import com.survivalcoding.gangnam2kiandroidstudy.data.datasource.local.IngredientDataSourceImpl
import com.survivalcoding.gangnam2kiandroidstudy.data.datasource.ProcedureDataSource
import com.survivalcoding.gangnam2kiandroidstudy.data.datasource.local.ProcedureDataSourceImpl
import com.survivalcoding.gangnam2kiandroidstudy.data.datasource.RecipeDataSource
import com.survivalcoding.gangnam2kiandroidstudy.data.datasource.local.RecipeDataSourceImpl
import com.survivalcoding.gangnam2kiandroidstudy.domain.repository.BookmarkRepository
import com.survivalcoding.gangnam2kiandroidstudy.data.repository.BookmarkRepositoryImpl
import com.survivalcoding.gangnam2kiandroidstudy.domain.repository.ChefRepository
import com.survivalcoding.gangnam2kiandroidstudy.data.repository.ChefRepositoryImpl
import com.survivalcoding.gangnam2kiandroidstudy.domain.repository.IngredientRepository
import com.survivalcoding.gangnam2kiandroidstudy.data.repository.IngredientRepositoryImpl
import com.survivalcoding.gangnam2kiandroidstudy.domain.repository.ProcedureRepository
import com.survivalcoding.gangnam2kiandroidstudy.data.repository.ProcedureRepositoryImpl
import com.survivalcoding.gangnam2kiandroidstudy.domain.repository.RecipeRepository
import com.survivalcoding.gangnam2kiandroidstudy.data.repository.RecipeRepositoryImpl
import com.survivalcoding.gangnam2kiandroidstudy.presentation.home.HomeViewModel
import com.survivalcoding.gangnam2kiandroidstudy.presentation.recipedetail.RecipeDetailViewModel
import com.survivalcoding.gangnam2kiandroidstudy.presentation.savedrecipes.SavedRecipesViewModel
import com.survivalcoding.gangnam2kiandroidstudy.presentation.search.SearchViewModel

// 임의의 싱글톤 DI 컨테이너
object DependencyContainer {
    fun provideRecipeDataSource(context: Context): RecipeDataSource {
        return RecipeDataSourceImpl.getInstance(provideAssetManager(context))
    }

    fun provideIngredientDataSource(context: Context): IngredientDataSource {
        return IngredientDataSourceImpl.getInstance(provideAssetManager(context))
    }

    fun provideProcedureDataSource(context: Context): ProcedureDataSource {
        return ProcedureDataSourceImpl.getInstance(provideAssetManager(context))
    }

    fun provideBookmarkDataSource(): BookmarkDataSource {
        return BookmarkDataSourceImpl.getInstance()
    }

    fun provideChefDataSource(context: Context): ChefDataSource {
        return ChefDataSourceImpl.getInstance(provideAssetManager(context))
    }

    fun provideRecipeRepository(context: Context): RecipeRepository {
        return RecipeRepositoryImpl.getInstance(
            provideRecipeDataSource(context)
        )
    }

    fun provideIngredientRepository(context: Context): IngredientRepository {
        return IngredientRepositoryImpl.getInstance(
            provideIngredientDataSource(context)
        )
    }

    fun provideProcedureRepository(context: Context): ProcedureRepository {
        return ProcedureRepositoryImpl.getInstance(
            provideProcedureDataSource(context)
        )
    }

    fun provideBookmarkRepository(): BookmarkRepository {
        return BookmarkRepositoryImpl.getInstance(
            provideBookmarkDataSource()
        )
    }

    fun provideChefRepository(context: Context): ChefRepository {
        return ChefRepositoryImpl.getInstance(provideChefDataSource(context))
    }

    fun provideAssetManager(context: Context) =
        AppAssetManagerImpl(context.assets)

    fun provideSavedRecipesViewModelFactory(context: Context) = viewModelFactory {
        initializer {
            SavedRecipesViewModel(
                recipeRepository = provideRecipeRepository(context.applicationContext),
                bookmarkRepository = provideBookmarkRepository()
            )
        }
    }

    fun provideSearchViewModelFactory(context: Context) = viewModelFactory {
        initializer {
            SearchViewModel(
                recipeRepository = provideRecipeRepository(context.applicationContext)
            )
        }
    }

    fun provideHomeViewModelFactory(context: Context) = viewModelFactory {
        initializer {
            HomeViewModel(
                recipeRepository = provideRecipeRepository(context.applicationContext)
            )
        }
    }

    fun provideRecipeDetailViewModelFactory(context: Context, route: Route.RecipeDetails) = viewModelFactory {
        initializer {
            val savedStateHandle = createSavedStateHandle()
            savedStateHandle["recipeId"] = route.recipeId
            RecipeDetailViewModel(
                recipeRepository = provideRecipeRepository(context.applicationContext),
                chefRepository = provideChefRepository(context.applicationContext),
                ingredientRepository = provideIngredientRepository(context.applicationContext),
                procedureRepository = provideProcedureRepository(context.applicationContext),
                savedStateHandle
            )
        }
    }
}