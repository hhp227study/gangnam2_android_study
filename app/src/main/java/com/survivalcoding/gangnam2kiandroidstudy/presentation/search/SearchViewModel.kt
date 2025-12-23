package com.survivalcoding.gangnam2kiandroidstudy.presentation.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.survivalcoding.gangnam2kiandroidstudy.domain.usecase.GetAllRecipesUseCase
import com.survivalcoding.gangnam2kiandroidstudy.domain.usecase.SearchRecipeByKeywordUseCase
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class SearchViewModel(
    private val getAllRecipesUseCase: GetAllRecipesUseCase,
    private val searchRecipeByKeywordUseCase: SearchRecipeByKeywordUseCase
) : ViewModel() {
    private val _uiState = MutableStateFlow(SearchUiState())
    val uiState = _uiState.asStateFlow()

    private val _event = MutableSharedFlow<SearchEvent>()
    val event = _event.asSharedFlow()

    private fun fetchAllRecipes() {
        _uiState.update { it.copy(isLoading = true) }
        viewModelScope.launch {
            getAllRecipesUseCase()
                .onSuccess { recipes ->
                    _uiState.update {
                        it.copy(isLoading = false, allRecipes = recipes)
                    }
                }
                .onFailure {
                    _uiState.update { it.copy(isLoading = false) }
                    emitEvent(
                        SearchEvent.ShowMessage(it.message ?: "Load failed")
                    )
                }
        }
    }

    private suspend fun searchRecipeByKeyword(keyword: String) {
        if (keyword.isEmpty()) return
        searchRecipeByKeywordUseCase(keyword)
            .onSuccess { recipes ->
                _uiState.update { it.copy(filteredRecipes = recipes) }
            }
            .onFailure {
                emitEvent(
                    SearchEvent.ShowMessage(it.message ?: "Search failed")
                )
            }
    }

    @OptIn(FlowPreview::class)
    private fun subscribeSearchKeyword() {
        viewModelScope.launch {
            uiState
                .map { it.searchKeyword }
                .debounce(500)
                .collectLatest { keyword ->
                    searchRecipeByKeyword(keyword)
                }
        }
    }

    private fun emitEvent(event: SearchEvent) {
        viewModelScope.launch { _event.emit(event) }
    }

    fun onAction(action: SearchAction) {
        when (action) {
            is SearchAction.SearchKeywordChange -> {
                _uiState.update { it.copy(searchKeyword = action.keyword) }
            }
            SearchAction.FilterButtonClick -> {
                emitEvent(SearchEvent.ShowFilterBottomSheet)
            }
            is SearchAction.FilterApply -> {
                _uiState.update { it.copy(filterSearchState = action.filter) }

                emitEvent(SearchEvent.HideFilterBottomSheet)
                emitEvent(SearchEvent.ShowMessage("필터가 적용되었습니다"))
            }
            SearchAction.FilterCancel -> {
                emitEvent(SearchEvent.HideFilterBottomSheet)
                emitEvent(SearchEvent.ShowMessage("필터가 취소되었습니다"))
            }
            SearchAction.BackClick -> {
                emitEvent(SearchEvent.NavigateBack)
            }
        }
    }

    init {
        fetchAllRecipes()
        subscribeSearchKeyword()
    }
}