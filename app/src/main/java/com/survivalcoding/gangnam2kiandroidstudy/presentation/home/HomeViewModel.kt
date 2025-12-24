package com.survivalcoding.gangnam2kiandroidstudy.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.survivalcoding.gangnam2kiandroidstudy.domain.model.Recipe
import com.survivalcoding.gangnam2kiandroidstudy.domain.usecase.GetFilteredRecipesUseCase
import com.survivalcoding.gangnam2kiandroidstudy.domain.usecase.ToggleBookmarkUseCase
import com.survivalcoding.gangnam2kiandroidstudy.presentation.home.HomeEvent.SearchFocusChanged
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class HomeViewModel(
    private val getFilteredRecipesUseCase: GetFilteredRecipesUseCase,
    private val toggleBookmarkUseCase: ToggleBookmarkUseCase
) : ViewModel() {
    private val _event = MutableSharedFlow<HomeEvent>()
    val event = _event.asSharedFlow()

    private val _uiState = MutableStateFlow(HomeUiState(isLoading = true))
    val uiState = _uiState.asStateFlow()

    private fun subscribeSelectedCategoryFlow() {
        viewModelScope.launch {
            _uiState.map { it.selectedCategory }
                .flatMapLatest { category ->
                    getFilteredRecipesUseCase(category)
                }
                .collectLatest { result ->
                    result
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
        }
    }

    private fun toggleBookmark(recipe: Recipe) {
        viewModelScope.launch {
            toggleBookmarkUseCase(recipe)
                .onSuccess {
                    _event.emit(
                        HomeEvent.ShowMessage("Saved to bookmarks")
                    )
                }
                .onFailure {
                    _event.emit(
                        HomeEvent.ShowMessage("Failed to save")
                    )
                }
        }
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
                    _event.emit(SearchFocusChanged(action.focused))
                }
            }
            is HomeAction.BookmarkClick -> {
                toggleBookmark(action.recipe)
            }
        }
    }

    init {
        subscribeSelectedCategoryFlow()
    }
}