package com.survivalcoding.gangnam2kiandroidstudy.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.survivalcoding.gangnam2kiandroidstudy.data.repository.RecipeRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class HomeViewModel(
    private val recipeRepository: RecipeRepository
) : ViewModel() {
    private val _uiState = MutableStateFlow(HomeUiState(isLoading = true))
    val uiState = _uiState.asStateFlow()

    private fun subscribeSelectedCategoryFlow() {
        viewModelScope.launch {
            _uiState.map { it.selectedCategory }
                .collectLatest { category ->
                    fetchFilteredRecipesByCategory(category)
                }
        }
    }

    private suspend fun fetchFilteredRecipesByCategory(category: String) {
        _uiState.update { state -> state.copy(isLoading = true) }
        recipeRepository.getFilteredRecipesByCategory(category)
            .onSuccess { recipes ->
                _uiState.update { state ->
                    state.copy(
                        isLoading = false,
                        recipes = recipes
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

    fun onSelectCategory(value: String) {
        _uiState.update { it.copy(selectedCategory = value) }
    }

    init {
        subscribeSelectedCategoryFlow()
    }
}