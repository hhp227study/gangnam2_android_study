package com.survivalcoding.gangnam2kiandroidstudy.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.survivalcoding.gangnam2kiandroidstudy.domain.usecase.GetFilteredRecipesUseCase
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class HomeViewModel(
    private val getFilteredRecipesUseCase: GetFilteredRecipesUseCase
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
        getFilteredRecipesUseCase(category)
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

    private fun onSelectCategory(value: String) {
        _uiState.update { it.copy(selectedCategory = value) }
    }

    private fun onSearchKeywordChange(value: String) {
        _uiState.update { it.copy(searchKeyword = value) }
    }

    fun onAction(action: HomeAction) {
        when (action) {
            is HomeAction.ChangeQuery -> onSearchKeywordChange(action.query)
            is HomeAction.SelectCategory -> onSelectCategory(action.category)
        }
    }

    init {
        subscribeSelectedCategoryFlow()
    }
}