package com.survivalcoding.gangnam2kiandroidstudy.presentation.home

sealed interface HomeAction {
    data class ChangeQuery(val query: String) : HomeAction
    data class SelectCategory(val category: String) : HomeAction
    data class SearchFocusChanged(val focused: Boolean) : HomeAction
}