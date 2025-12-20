package com.survivalcoding.gangnam2kiandroidstudy.presentation.search

sealed interface SearchAction {
    data class FilterApply(val filterSearchState: FilterSearchState) : SearchAction
    data class FilterButtonClick(val isClick: Boolean) : SearchAction
    data class SearchKeywordChange(val keyword: String) : SearchAction
}