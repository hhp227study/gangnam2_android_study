package com.survivalcoding.gangnam2kiandroidstudy.presentation.savedrecipes

import com.survivalcoding.gangnam2kiandroidstudy.data.MockRecipeDataSource
import com.survivalcoding.gangnam2kiandroidstudy.data.model.Recipe
import com.survivalcoding.gangnam2kiandroidstudy.data.repository.RecipeRepositoryImpl
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
    private val fakeJson = """
{
  "recipes":[
    {
      "category":"Indian",
      "id":1,
      "name":"Traditional spare ribs baked",
      "image":"https://cdn.pixabay.com/photo/2017/11/10/15/04/steak-2936531_1280.jpg",
      "chef":"Chef John",
      "time":"20 min",
      "rating":4.0
    },
    {
      "id":2,
      "category":"Asian",
      "name":"Spice roasted chicken with flavored rice",
      "image":"https://cdn.pixabay.com/photo/2018/12/04/16/49/tandoori-3856045_1280.jpg",
      "chef":"Mark Kelvin",
      "time":"20 min",
      "rating":4.0
    },
    {
      "id":3,
      "category":"Chinese",
      "name":"Spicy fried rice mix chicken bali",
      "image":"https://cdn.pixabay.com/photo/2019/09/07/19/02/spanish-paella-4459519_1280.jpg",
      "chef":"Spicy Nelly",
      "time":"20 min",
      "rating":4.0
    },
    {
      "category":"Japanese",
      "id":4,
      "name":"Ttekbokki",
      "image":"https://cdn.pixabay.com/photo/2017/07/27/16/48/toppokki-2545943_1280.jpg",
      "chef":"Kim Dahee",
      "time":"30 min",
      "rating":5.0
    }
  ]
}
    """.trimIndent()

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
        val dataSource = MockRecipeDataSource(fakeJson)
        val repository = RecipeRepositoryImpl.getInstance(dataSource)

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
        val dataSource = MockRecipeDataSource(fakeJson)
        val repository = RecipeRepositoryImpl.getInstance(dataSource)

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
}
