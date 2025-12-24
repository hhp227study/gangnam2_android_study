package com.survivalcoding.gangnam2kiandroidstudy.presentation.search

import androidx.activity.ComponentActivity
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import com.survivalcoding.gangnam2kiandroidstudy.core.di.testModule
import com.survivalcoding.gangnam2kiandroidstudy.core.routing.NavigationRoot
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.koin.core.context.GlobalContext.startKoin
import org.koin.core.context.GlobalContext.stopKoin
import org.koin.test.KoinTest

class SearchNavigationIntegrationTest : KoinTest {

    @get:Rule
    val composeRule = createAndroidComposeRule<ComponentActivity>()

    @Before
    fun setup() {
        stopKoin()
        startKoin {
            modules(testModule)
        }
    }

    @After
    fun tearDown() {
        stopKoin()
    }

    @Test
    fun 리스트에서_아이템_클릭_시_상세화면으로_이동하고_ID가_전달된다() {
        composeRule.setContent {
            NavigationRoot()
        }

        // 1️⃣ 리스트 화면 확인
        composeRule
            .onNodeWithText("Pasta")
            .assertIsDisplayed()

        // 2️⃣ 아이템 클릭
        composeRule
            .onNodeWithText("Pasta")
            .performClick()

        // 3️⃣ 상세 화면 이동 확인
        composeRule
            .onNodeWithText("Recipe Detail")
            .assertIsDisplayed()

        // 4️⃣ 전달된 ID 기반 데이터 표시 확인
        composeRule
            .onNodeWithText("Pasta")
            .assertIsDisplayed()
    }

    @Test
    fun 상세화면에서_뒤로가기_시_검색화면_상태가_유지된다() {
        composeRule.setContent {
            NavigationRoot()
        }

        // 상세 진입
        composeRule.onNodeWithText("Pizza").performClick()

        // 뒤로가기
        composeRule.activityRule.scenario.onActivity {
            it.onBackPressedDispatcher.onBackPressed()
        }

        // Search 화면 유지
        composeRule.onNodeWithText("Search recipes")
            .assertIsDisplayed()

        // 리스트 상태 유지
        composeRule.onNodeWithText("Pizza")
            .assertIsDisplayed()
    }
}