package com.survivalcoding.gangnam2kiandroidstudy.presentation.search

import androidx.activity.ComponentActivity
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performTextClearance
import androidx.compose.ui.test.performTextInput
import com.survivalcoding.gangnam2kiandroidstudy.core.di.testModule
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.TestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestWatcher
import org.junit.runner.Description
import org.koin.core.context.GlobalContext.startKoin
import org.koin.core.context.GlobalContext.stopKoin
import org.koin.test.KoinTest

@OptIn(ExperimentalCoroutinesApi::class)
class SearchRootIntegrationTest : KoinTest {

    @get:Rule
    val composeRule = createAndroidComposeRule<ComponentActivity>()

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    @Before
    fun setUp() {
        stopKoin()

        startKoin {
            modules(testModule)
        }

        composeRule.setContent {
            SearchRoot(
                onRecipeClick = {},
                onBackClick = {}
            )
        }
    }

    @After
    fun tearDown() {
        stopKoin()
    }

    // ----------------------------------------------------------
    // 1️⃣ 앱 진입 시 전체 레시피 표시
    // ----------------------------------------------------------
    @Test
    fun 앱_진입_시_FakeRepository_데이터가_화면에_표시된다() {
        composeRule.waitForIdle()

        composeRule.onNodeWithText("Pasta").assertIsDisplayed()
        composeRule.onNodeWithText("Pizza").assertIsDisplayed()
        composeRule.onNodeWithText("Burger").assertIsDisplayed()
    }

    // ----------------------------------------------------------
    // 2️⃣ 검색어 입력 → debounce → 결과 표시
    // ----------------------------------------------------------
    @Test
    fun 검색어_입력_후_debounce_이후_검색_결과가_표시된다() {
        // Given
        composeRule
            .onNodeWithText("Search recipe")
            .performTextInput("Pa")

        // When (debounce 500ms)
        composeRule.mainClock.advanceTimeBy(600)
        composeRule.waitForIdle()

        // Then
        composeRule.onNodeWithText("Pasta").assertIsDisplayed()
        composeRule.onNodeWithText("Pizza").assertDoesNotExist()
        composeRule.onNodeWithText("Burger").assertDoesNotExist()

        composeRule.onNodeWithText("1 results").assertIsDisplayed()
    }

    // ----------------------------------------------------------
    // 3️⃣ 검색어 변경 시 결과가 다시 갱신된다
    // ----------------------------------------------------------
    @Test
    fun 검색어_변경_시_결과가_새로_갱신된다() {
        composeRule
            .onNodeWithText("Search recipe")
            .performTextInput("P")

        composeRule.mainClock.advanceTimeBy(600)
        composeRule.waitForIdle()

        composeRule.onNodeWithText("Pasta").assertIsDisplayed()
        composeRule.onNodeWithText("Pizza").assertIsDisplayed()

        // 검색어 변경
        composeRule
            .onNodeWithText("Search recipe")
            .performTextClearance()

        composeRule
            .onNodeWithText("Search recipe")
            .performTextInput("Bur")

        composeRule.mainClock.advanceTimeBy(600)
        composeRule.waitForIdle()

        composeRule.onNodeWithText("Burger").assertIsDisplayed()
        composeRule.onNodeWithText("Pasta").assertDoesNotExist()
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    class MainDispatcherRule(
        private val dispatcher: TestDispatcher = StandardTestDispatcher()
    ) : TestWatcher() {

        override fun starting(description: Description) {
            Dispatchers.setMain(dispatcher)
        }

        override fun finished(description: Description) {
            Dispatchers.resetMain()
        }
    }
}