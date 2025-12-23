package com.survivalcoding.gangnam2kiandroidstudy.presentation.search

sealed interface SearchAction {
    data class SearchKeywordChange(val keyword: String) : SearchAction
    object FilterButtonClick : SearchAction
    data class FilterApply(val filter: FilterSearchState) : SearchAction
    object FilterCancel : SearchAction
    object BackClick : SearchAction
}