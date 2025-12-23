package com.survivalcoding.gangnam2kiandroidstudy.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.survivalcoding.gangnam2kiandroidstudy.domain.usecase.GetFilteredRecipesUseCase
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class HomeViewModel(
    private val getFilteredRecipesUseCase: GetFilteredRecipesUseCase
) : ViewModel() {
    private val _event = MutableSharedFlow<HomeEvent>()
    val event = _event.asSharedFlow()

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
            is HomeAction.ChangeQuery -> {
                _uiState.update { it.copy(searchKeyword = action.query) }
            }
            is HomeAction.SelectCategory -> {
                _uiState.update { it.copy(selectedCategory = action.category) }
            }
            is HomeAction.SearchFocusChanged -> {
                viewModelScope.launch {
                    _event.emit(HomeEvent.SearchFocusChanged(action.focused))
                }
            }
        }
    }

    init {
        subscribeSelectedCategoryFlow()
    }
}