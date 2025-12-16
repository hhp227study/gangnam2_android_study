package com.survivalcoding.gangnam2kiandroidstudy.presentation.savedrecipes

import com.survivalcoding.gangnam2kiandroidstudy.data.MockRecipeDataSource
import com.survivalcoding.gangnam2kiandroidstudy.data.MockRecipesRepositoryFailure
import com.survivalcoding.gangnam2kiandroidstudy.data.repository.RecipeRepositoryImpl
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertFalse
import junit.framework.TestCase.assertNull
import junit.framework.TestCase.assertTrue
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.UnconfinedTestDispatcher
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
    fun `fetchRecipes_성공_시_uiState가_recipes로_업데이트된다`() = runTest {
        // Given
        val dataSource = MockRecipeDataSource(fakeJson)
        val repository = RecipeRepositoryImpl.getInstance(dataSource)
        val viewModel = SavedRecipesViewModel(repository)

        // When
        // init{} 안에서 fetchRecipes() 실행 → 비동기 → Idle 시점까지 진척
        testScheduler.advanceUntilIdle()

        // Then
        val state = viewModel.uiState.value

        assertFalse(state.isLoading)
        assertEquals(4, state.recipes.size)              // JSON 기반으로 총 4개
        assertNull(state.message)

        // 첫 번째 레시피 이름 검증
        assertEquals("Traditional spare ribs baked", state.recipes[0].name)
    }

    @Test
    fun `fetchRecipes_실패_시_uiState가_message로_업데이트된다`() = runTest {
        // Given
        val repository = MockRecipesRepositoryFailure("Network error")
        val viewModel = SavedRecipesViewModel(repository)

        // When
        testScheduler.advanceUntilIdle()

        // Then
        val state = viewModel.uiState.value

        assertFalse(state.isLoading)
        assertTrue(state.recipes.isEmpty())
        assertEquals("Network error", state.message)
    }
}
