package com.survivalcoding.gangnam2kiandroidstudy.presentation.signup

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.survivalcoding.gangnam2kiandroidstudy.domain.usecase.GoogleSignInUseCase
import com.survivalcoding.gangnam2kiandroidstudy.domain.usecase.SignUpUseCase
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class SignUpViewModel(
    private val signUpUseCase: SignUpUseCase,
    private val googleSignInUseCase: GoogleSignInUseCase
) : ViewModel() {
    private val _uiState = MutableStateFlow(SignUpUiState())
    val uiState = _uiState.asStateFlow()

    private val _event = MutableSharedFlow<SignUpEvent>()
    val event = _event.asSharedFlow()

    private fun signUp(name: String, email: String, password: String) {
        _uiState.update { it.copy(isLoading = true) }

        viewModelScope.launch {
            signUpUseCase(email, password)
                .onSuccess {
                    _uiState.update { it.copy(isLoading = false) }
                    emitEvent(SignUpEvent.NavigateToMain)
                }
                .onFailure {
                    _uiState.update { it.copy(isLoading = false) }
                    emitEvent(
                        SignUpEvent.ShowMessage(
                            it.localizedMessage ?: "Sign up failed"
                        )
                    )
                }
        }
    }

    private fun googleSignIn() {
        viewModelScope.launch {
            /*googleSignInUseCase()
                .onSuccess {
                    emitEvent(SignUpEvent.NavigateToMain)
                }
                .onFailure {
                    emitEvent(
                        SignUpEvent.ShowMessage(
                            it.localizedMessage ?: "Google sign in failed"
                        )
                    )
                }*/
        }
    }

    private fun emitEvent(event: SignUpEvent) {
        viewModelScope.launch { _event.emit(event) }
    }

    fun onAction(action: SignUpAction) {
        when (action) {
            is SignUpAction.NameChanged ->
                _uiState.update { it.copy(name = action.name) }
            is SignUpAction.EmailChanged ->
                _uiState.update { it.copy(email = action.email) }
            is SignUpAction.PasswordChanged ->
                _uiState.update { it.copy(password = action.password) }
            is SignUpAction.ConfirmPasswordChanged ->
                _uiState.update { it.copy(confirmPassword = action.confirmPassword) }
            is SignUpAction.SignUpClicked ->
                signUp(action.name, action.email, action.password)
            SignUpAction.GoogleSignInClicked ->
                googleSignIn()
            SignUpAction.SignInClicked ->
                emitEvent(SignUpEvent.NavigateToSignIn)
            SignUpAction.FacebookSignInClicked -> {
                // TODO
            }
            is SignUpAction.TermsChecked ->
                _uiState.update { it.copy(isTermsChecked = action.checked) }
        }
    }
}