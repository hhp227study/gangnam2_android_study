package com.survivalcoding.gangnam2kiandroidstudy.presentation.recipedetail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.survivalcoding.gangnam2kiandroidstudy.domain.repository.ChefRepository
import com.survivalcoding.gangnam2kiandroidstudy.domain.repository.IngredientRepository
import com.survivalcoding.gangnam2kiandroidstudy.domain.repository.ProcedureRepository
import com.survivalcoding.gangnam2kiandroidstudy.domain.repository.RecipeRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class RecipeDetailViewModel(
    private val recipeRepository: RecipeRepository,
    private val chefRepository: ChefRepository,
    private val ingredientRepository: IngredientRepository,
    private val procedureRepository: ProcedureRepository, // GetRecipeDetailsUseCase 는 추후에 리팩토링으로 작성
    savedStateHandle: SavedStateHandle
) : ViewModel() {
    private val _uiState = MutableStateFlow(RecipeDetailUiState())
    val uiState = _uiState.asStateFlow()

    val recipeId = savedStateHandle.get<Int>("recipeId") ?: -1

    private fun loadChef(name: String) {
        _uiState.update { it.copy(isLoading = true) }
        viewModelScope.launch {
            chefRepository.getChefByName(name)
                .onSuccess { chef ->
                    _uiState.update { it.copy(isLoading = false, chef = chef) }
                }
                .onFailure {
                    _uiState.update { state -> state.copy(isLoading = false, message = it.message) }
                }
        }
    }

    private fun loadRecipeDetail(recipeId: Int) {
        _uiState.update { it.copy(isLoading = true) }
        viewModelScope.launch {
            launch {
                recipeRepository.getRecipeById(recipeId)
                    .onSuccess { recipe ->
                        if (recipe != null) {
                            loadChef(recipe.chef)
                            _uiState.update { it.copy(isLoading = false, recipe = recipe) }
                        }
                    }
                    .onFailure {
                        _uiState.update { state -> state.copy(isLoading = false, message = it.message) }
                    }
            }
            launch {
                ingredientRepository.getIngredientByRecipeId(recipeId)
                    .onSuccess { ingredients ->
                        _uiState.update { it.copy(isLoading = false, ingredients = ingredients) }
                    }
                    .onFailure {
                        _uiState.update { state -> state.copy(isLoading = false, message = it.message) }
                    }
            }
            launch {
                procedureRepository.getProcedureByRecipeId(recipeId)
                    .onSuccess { procedures ->
                        _uiState.update { it.copy(isLoading = false, procedures = procedures) }
                    }
                    .onFailure {
                        _uiState.update { state -> state.copy(isLoading = false, message = it.message) }
                    }
            }
        }
    }

    fun onTabClick(value: Int) {
        _uiState.update { it.copy(selectedTabPosition = value) }
    }

    init {
        loadRecipeDetail(recipeId)
    }
}