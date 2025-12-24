package com.survivalcoding.gangnam2kiandroidstudy.presentation.home

import com.survivalcoding.gangnam2kiandroidstudy.domain.model.Recipe

sealed interface HomeAction {
    data class ChangeQuery(val query: String) : HomeAction
    data class SelectCategory(val category: String) : HomeAction
    data class SearchFocusChanged(val focused: Boolean) : HomeAction
    data class BookmarkClick(val recipe: Recipe) : HomeAction
}