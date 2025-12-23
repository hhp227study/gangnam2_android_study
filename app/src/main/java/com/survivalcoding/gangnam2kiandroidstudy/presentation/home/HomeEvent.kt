package com.survivalcoding.gangnam2kiandroidstudy.presentation.home

sealed interface HomeEvent {
    data class SearchFocusChanged(val focused: Boolean) : HomeEvent
    data class ShowMessage(val message: String) : HomeEvent
}