package com.survivalcoding.gangnam2kiandroidstudy.presentation.recipedetail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.survivalcoding.gangnam2kiandroidstudy.domain.usecase.GetRecipeDetailsUseCase
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class RecipeDetailViewModel(
    private val getRecipeDetailsUseCase: GetRecipeDetailsUseCase,
    savedStateHandle: SavedStateHandle
) : ViewModel() {
    private val _event = MutableSharedFlow<RecipeDetailEvent>()
    val event = _event.asSharedFlow()

    private val _uiState = MutableStateFlow(RecipeDetailUiState())
    val uiState = _uiState.asStateFlow()

    val recipeId = savedStateHandle.get<Int>("recipeId") ?: -1

    private fun loadRecipeDetail(recipeId: Int) {
        _uiState.update { it.copy(isLoading = true) }
        viewModelScope.launch {
            getRecipeDetailsUseCase(recipeId)
                .onSuccess { (pair, ingredient, procedure) ->
                    _uiState.update {
                        it.copy(
                            isLoading = false,
                            recipe = pair.first,
                            ingredients = ingredient,
                            procedures = procedure,
                            chef = pair.second
                        )
                    }
                }
                .onFailure {
                    _uiState.update { state -> state.copy(isLoading = false, message = it.message) }
                }
        }
    }

    private fun followChef(chefId: Int) {
        viewModelScope.launch {
            // followUseCase(chefId)
            emitEvent(RecipeDetailEvent.FollowCompleted(chefId))
        }
    }

    private fun emitEvent(event: RecipeDetailEvent) {
        viewModelScope.launch { _event.emit(event) }
    }

    fun onAction(action: RecipeDetailAction) {
        when (action) {
            is RecipeDetailAction.TabClick -> {
                _uiState.update { it.copy(selectedTabPosition = action.position) }
            }
            is RecipeDetailAction.FollowClick -> {
                followChef(action.chefId)
            }
            RecipeDetailAction.BackClick -> {
                emitEvent(RecipeDetailEvent.NavigateUp)
            }
        }
    }

    init {
        loadRecipeDetail(recipeId)
    }
}