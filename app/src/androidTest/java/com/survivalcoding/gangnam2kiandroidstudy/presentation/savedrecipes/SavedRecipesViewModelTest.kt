package com.survivalcoding.gangnam2kiandroidstudy.presentation.savedrecipes

import com.survivalcoding.gangnam2kiandroidstudy.data.model.Recipe
import com.survivalcoding.gangnam2kiandroidstudy.data.repository.RecipeRepository
import com.survivalcoding.gangnam2kiandroidstudy.presentation.savedRecipes.SavedRecipesUiState
import com.survivalcoding.gangnam2kiandroidstudy.presentation.savedRecipes.SavedRecipesViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class SavedRecipesViewModelFakeTest {
    private val dispatcher = UnconfinedTestDispatcher()

    @Before
    fun setup() {
        Dispatchers.setMain(dispatcher)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `fetchRecipes는_성공_시_uiState를_업데이트_하는지_테스트`() = runTest {
        // given
        val fakeRecipes = listOf(
            Recipe(1, "Pizza"),
            Recipe(2, "Burger")
        )
        val repository = FakeRecipeRepository(Result.success(fakeRecipes))

        // when
        val viewModel = SavedRecipesViewModel(repository)

        // then
        val list = mutableListOf<SavedRecipesUiState>()
        val job = launch {
            viewModel.uiState.collect { list.add(it) }
        }

        advanceUntilIdle()

        assert(list[0].isLoading == false)          // initial
        assert(list[1].isLoading == true)           // loading
        assert(list[2].recipes == fakeRecipes)      // success
        assert(list[2].isLoading == false)

        job.cancel()
    }

    @Test
    fun `fetchRecipes는_실패_시_uiState를_업데이트_하는지_테스트`() = runTest {
        // given
        val error = Exception("Network error")
        val repository = FakeRecipeRepository(Result.failure(error))

        // when
        val viewModel = SavedRecipesViewModel(repository)

        // then
        val list = mutableListOf<SavedRecipesUiState>()
        val job = launch {
            viewModel.uiState.collect { list.add(it) }
        }

        advanceUntilIdle()

        assert(list[0].isLoading == false)
        assert(list[1].isLoading == true)
        assert(list[2].isLoading == false)
        assert(list[2].message == "Network error")

        job.cancel()
    }

    class FakeRecipeRepository(
        private val result: Result<List<Recipe>>
    ) : RecipeRepository {
        override suspend fun getAllRecipes(): Result<List<Recipe>> {
            return result
        }
    }
}
