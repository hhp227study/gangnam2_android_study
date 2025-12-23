package com.survivalcoding.gangnam2kiandroidstudy.presentation.splash

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.survivalcoding.gangnam2kiandroidstudy.domain.model.NetworkStatus
import com.survivalcoding.gangnam2kiandroidstudy.domain.usecase.ObserveNetworkStatusUseCase
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class SplashViewModel(
    private val observeNetworkStatusUseCase: ObserveNetworkStatusUseCase
) : ViewModel() {
    private val _uiState = MutableStateFlow(SplashUiState())
    val uiState: StateFlow<SplashUiState> = _uiState

    private val _uiEvent = MutableSharedFlow<SplashUiEvent>()
    val uiEvent: SharedFlow<SplashUiEvent> = _uiEvent

    private var lastStatus: NetworkStatus? = null

    private fun observeNetwork() {
        viewModelScope.launch {
            observeNetworkStatusUseCase().collect { status ->
                when (status) {
                    NetworkStatus.Connected -> {
                        _uiState.update {
                            it.copy(isStartButtonEnabled = true)
                        }
                        if (lastStatus == NetworkStatus.Disconnected) {
                            _uiEvent.emit(
                                SplashUiEvent.ShowSnackbar("네트워크가 다시 연결되었습니다")
                            )
                        }
                    }
                    NetworkStatus.Disconnected -> {
                        _uiState.update {
                            it.copy(isStartButtonEnabled = false)
                        }
                        _uiEvent.emit(
                            SplashUiEvent.ShowSnackbar("네트워크가 연결되지 않았습니다")
                        )
                    }
                }
                lastStatus = status
            }
        }
    }

    init {
        observeNetwork()
    }
}