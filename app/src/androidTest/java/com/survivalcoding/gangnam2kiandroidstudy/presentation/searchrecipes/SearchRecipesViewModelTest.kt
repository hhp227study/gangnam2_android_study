package com.survivalcoding.gangnam2kiandroidstudy.presentation.searchrecipes

import com.survivalcoding.gangnam2kiandroidstudy.data.MockRecipeDataSource
import com.survivalcoding.gangnam2kiandroidstudy.data.MockRecipeRepository
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.*
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestWatcher

@OptIn(ExperimentalCoroutinesApi::class)
class SearchRecipesViewModelTest {

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    private val fakeJson = """
{
  "recipes":[
    {
      "category":"Indian",
      "id":1,
      "name":"Traditional spare ribs baked",
      "image":"img1",
      "chef":"Chef John",
      "time":"20 min",
      "rating":4.0
    },
    {
      "id":2,
      "category":"Asian",
      "name":"Spice roasted chicken with flavored rice",
      "image":"img2",
      "chef":"Mark Kelvin",
      "time":"20 min",
      "rating":4.0
    },
    {
      "id":3,
      "category":"Chinese",
      "name":"Spicy fried rice mix chicken bali",
      "image":"img3",
      "chef":"Spicy Nelly",
      "time":"20 min",
      "rating":4.0
    },
    {
      "category":"Japanese",
      "id":4,
      "name":"Ttekbokki",
      "image":"img4",
      "chef":"Kim Dahee",
      "time":"30 min",
      "rating":5.0
    }
  ]
}
    """.trimIndent()

    // ----------------------------------------------------------
    // 1) fetchAllRecipes 성공 테스트
    // ----------------------------------------------------------
    @Test
    fun `fetchAllRecipes_성공_시_UI_State_업데이트`() = runTest {
        // Given
        val dataSource = MockRecipeDataSource(fakeJson)
        val repository = MockRecipeRepository(dataSource)
        val viewModel = SearchRecipesViewModel(repository)

        // 초기 fetchAllRecipes, fetchFilteredRecipes 안정화
        advanceUntilIdle()

        // Then
        val state = viewModel.uiState.value
        assertEquals(false, state.isLoading)
        assertEquals(dataSource.getRecipes(), state.allRecipes)
    }

    // ----------------------------------------------------------
    // 2) fetchAllRecipes 실패 테스트
    // ----------------------------------------------------------
    @Test
    fun `fetchAllRecipes_실패_시_message_업데이트`() = runTest {
        // Given
        val dataSource = MockRecipeDataSource(fakeJson)
        val repository = MockRecipeRepository(
            dataSource,
            shouldFailAllRecipes = true,
            failureMessage = "network error"
        )
        val viewModel = SearchRecipesViewModel(repository)

        // When
        advanceUntilIdle()

        // Then
        val state = viewModel.uiState.value
        assertEquals("network error", state.message)
    }

    // ----------------------------------------------------------
    // 3) debounce 적용 후 filteredRecipes 업데이트 테스트
    // ----------------------------------------------------------
    @Test
    fun `keyword_입력_시_debounce_500ms_후_filteredRecipes_업데이트`() = runTest {
        val dataSource = MockRecipeDataSource(fakeJson)
        val repository = MockRecipeRepository(dataSource)

        val viewModel = SearchRecipesViewModel(repository)
        advanceUntilIdle()

        // When
        viewModel.onSearchKeywordChange("sp")   // "Spice roasted..." "Spicy fried..." 2개 매칭

        advanceTimeBy(500)  // debounce 500ms
        advanceUntilIdle()

        val expected = dataSource.getRecipes().filter {
            it.name.lowercase().contains("sp") ||
                    it.chef.lowercase().contains("sp")
        }

        // Then
        val state = viewModel.uiState.value
        assertEquals(expected, state.filteredRecipes)
    }

    // ----------------------------------------------------------
    // 4) isShowBottomSheet 변경 확인
    // ----------------------------------------------------------
    @Test
    fun `onFilterButtonClick_값_변경_테스트`() = runTest {
        val dataSource = MockRecipeDataSource(fakeJson)
        val repository = MockRecipeRepository(dataSource)
        val viewModel = SearchRecipesViewModel(repository)

        // When
        viewModel.onFilterButtonClick(true)

        // Then
        assertEquals(true, viewModel.uiState.value.isShowBottomSheet)
    }

    // ----------------------------------------------------------
    // 5) filterSearchState 변경 확인
    // ----------------------------------------------------------
    @Test
    fun `onFilterComplete_호출_시_filterSearchState_변경`() = runTest {
        val dataSource = MockRecipeDataSource(fakeJson)
        val repository = MockRecipeRepository(dataSource)
        val viewModel = SearchRecipesViewModel(repository)

        val filterState = FilterSearchState(
            time = "10min",
            rate = 4,
            category = "Korean"
        )

        // When
        viewModel.onFilterComplete(filterState)

        // Then
        assertEquals(filterState, viewModel.uiState.value.filterSearchState)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    class MainDispatcherRule(
        private val dispatcher: TestDispatcher = StandardTestDispatcher()
    ) : TestWatcher() {

        override fun starting(description: org.junit.runner.Description) {
            Dispatchers.setMain(dispatcher)
        }

        override fun finished(description: org.junit.runner.Description) {
            Dispatchers.resetMain()
        }
    }
}