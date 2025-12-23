package com.survivalcoding.gangnam2kiandroidstudy.presentation.savedrecipes

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.survivalcoding.gangnam2kiandroidstudy.domain.usecase.GetSavedRecipesUseCase
import com.survivalcoding.gangnam2kiandroidstudy.domain.usecase.RemoveBookmarkUseCase
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class SavedRecipesViewModel(
    private val getSavedRecipesUseCase: GetSavedRecipesUseCase,
    private val removeBookmarkUseCase: RemoveBookmarkUseCase
) : ViewModel() {
    private val _uiState = MutableStateFlow(SavedRecipesUiState())
    val uiState = _uiState.asStateFlow()

    private val _event = MutableSharedFlow<SavedRecipesEvent>()
    val event = _event.asSharedFlow()

    private fun fetchBookmarkRecipes() {
        _uiState.update { state -> state.copy(isLoading = true) }
        viewModelScope.launch {
            getSavedRecipesUseCase()
                .onSuccess { recipes ->
                    _uiState.update { state ->
                        state.copy(
                            isLoading = false,
                            recipes = recipes
                        )
                    }
                }
                .onFailure {
                    emitEvent(
                        SavedRecipesEvent.ShowMessage(
                            it.message ?: "Failed to load saved recipes"
                        )
                    )
                }
        }
    }

    private fun removeBookmark(id: Int) {
        viewModelScope.launch {
            removeBookmarkUseCase(id)
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
                    emitEvent(
                        SavedRecipesEvent.ShowMessage(
                            it.message ?: "Failed to remove bookmark"
                        )
                    )
                }
        }
    }

    private fun emitEvent(event: SavedRecipesEvent) {
        viewModelScope.launch { _event.emit(event) }
    }

    fun onAction(action: SavedRecipesAction) {
        when (action) {
            is SavedRecipesAction.RemoveBookmark -> removeBookmark(action.id)
            is SavedRecipesAction.RecipeClick -> {
                emitEvent(SavedRecipesEvent.NavigateToRecipe(action.id))
            }
        }
    }

    init {
        fetchBookmarkRecipes()
    }
}