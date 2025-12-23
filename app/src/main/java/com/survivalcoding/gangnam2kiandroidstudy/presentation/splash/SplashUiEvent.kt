package com.survivalcoding.gangnam2kiandroidstudy.presentation.splash

sealed interface SplashUiEvent {
    data class ShowSnackbar(val message: String) : SplashUiEvent
}