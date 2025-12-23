package com.survivalcoding.gangnam2kiandroidstudy.presentation.search

sealed interface SearchEvent {
    object ShowFilterBottomSheet : SearchEvent
    object HideFilterBottomSheet : SearchEvent
    data class ShowMessage(val message: String) : SearchEvent
    object NavigateBack : SearchEvent
}