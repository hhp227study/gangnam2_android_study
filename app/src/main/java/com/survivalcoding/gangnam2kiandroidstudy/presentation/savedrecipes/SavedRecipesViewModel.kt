package com.survivalcoding.gangnam2kiandroidstudy.presentation.savedrecipes

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.survivalcoding.gangnam2kiandroidstudy.domain.repository.BookmarkRepository
import com.survivalcoding.gangnam2kiandroidstudy.domain.repository.RecipeRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class SavedRecipesViewModel(
    private val recipeRepository: RecipeRepository,
    private val bookmarkRepository: BookmarkRepository
) : ViewModel() {
    private val _uiState = MutableStateFlow(SavedRecipesUiState())
    val uiState = _uiState.asStateFlow()

    private fun fetchRecipes(ids: List<Int>) {
        _uiState.update { state -> state.copy(isLoading = true) }
        viewModelScope.launch {
            recipeRepository.getAllRecipes()
                .onSuccess { recipes ->
                    _uiState.update { state ->
                        state.copy(
                            isLoading = false,
                            recipes = recipes.filter { it.id in ids }
                        )
                    }
                }
                .onFailure {
                    _uiState.update { state ->
                        state.copy(
                            isLoading = false,
                            message = it.message
                        )
                    }
                }
        }
    }

    private fun fetchBookmarkRecipes() {
        _uiState.update { state -> state.copy(isLoading = true) }
        viewModelScope.launch {
            bookmarkRepository.getBookmarkedRecipeIds()
                .onSuccess { bookmarkIds ->
                    fetchRecipes(bookmarkIds)
                }
                .onFailure {
                    _uiState.update { state ->
                        state.copy(
                            isLoading = false,
                            message = it.message
                        )
                    }
                }
        }
    }

    fun removeBookmark(id: Int) {
        viewModelScope.launch {
            bookmarkRepository.removeBookmark(id)
                .onSuccess { success ->
                    if (success) {
                        _uiState.update { state ->
                            state.copy(
                                isLoading = false,
                                recipes = state.recipes.filter { it.id != id }
                            )
                        }
                    }
                }
                .onFailure {
                    _uiState.update { state ->
                        state.copy(
                            isLoading = false,
                            message = it.message
                        )
                    }
                }
        }
    }

    init {
        fetchBookmarkRecipes()
    }
}