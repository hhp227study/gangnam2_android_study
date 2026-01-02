package com.survivalcoding.gangnam2kiandroidstudy.presentation.signin

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.survivalcoding.gangnam2kiandroidstudy.domain.usecase.GoogleSignInUseCase
import com.survivalcoding.gangnam2kiandroidstudy.domain.usecase.SignInUseCase
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class SignInViewModel(
    private val signInUseCase: SignInUseCase,
    private val googleSignInUseCase: GoogleSignInUseCase
) : ViewModel() {
    private val _uiState = MutableStateFlow(SignInUiState())
    val uiState: StateFlow<SignInUiState> = _uiState.asStateFlow()

    private val _event = MutableSharedFlow<SignInEvent>()
    val event = _event.asSharedFlow()

    private fun signIn(email: String, password: String) {
        _uiState.update {
            it.copy(isLoading = true)
        }
        viewModelScope.launch {
            signInUseCase.invoke(email, password)
                .onSuccess {
                    _uiState.update { it.copy(isLoading = false) }
                    emitEvent(SignInEvent.NavigateToMain)
                }
                .onFailure {
                    _uiState.update { it.copy(isLoading = false) }
                    emitEvent(SignInEvent.ShowMessage(it.localizedMessage))
                }
        }
    }

    private fun emitEvent(event: SignInEvent) {
        viewModelScope.launch { _event.emit(event) }
    }

    fun onAction(action: SignInAction) {
        when (action) {
            is SignInAction.EmailChanged -> {
                _uiState.update {
                    it.copy(email = action.email)
                }
            }
            is SignInAction.PasswordChanged -> {
                _uiState.update {
                    it.copy(password = action.password)
                }
            }
            is SignInAction.SignInClicked -> {
                signIn(action.email, action.password)
            }
            SignInAction.GoogleSignInClicked -> {
                emitEvent(SignInEvent.GoogleLoginClick)
            }
            SignInAction.ForgotPasswordClicked -> {
                emitEvent(SignInEvent.NavigateToForgotPassword)
            }
            SignInAction.FacebookSignInClicked -> {
                // TODO
            }
            SignInAction.SignUpClicked -> emitEvent(SignInEvent.NavigateToSignUp)
            is SignInAction.GoogleIdTokenReceive -> {
                viewModelScope.launch {
                    googleSignInUseCase(action.id)
                        .onSuccess {
                            emitEvent(SignInEvent.NavigateToMain)
                        }
                        .onFailure {
                            emitEvent(SignInEvent.ShowMessage(it.localizedMessage))
                        }
                }
            }
        }
    }
}