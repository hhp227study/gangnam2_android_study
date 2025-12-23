package com.survivalcoding.gangnam2kiandroidstudy.presentation.search

import com.survivalcoding.gangnam2kiandroidstudy.domain.model.Recipe

data class SearchUiState(
    val searchKeyword: String = "",
    val isLoading: Boolean = false,
    val allRecipes: List<Recipe> = emptyList(),
    val filteredRecipes: List<Recipe> = emptyList(),
    val filterSearchState: FilterSearchState = FilterSearchState()
)