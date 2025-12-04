package com.survivalcoding.gangnam2kiandroidstudy.presentation.component

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class FilterButtonKtTest {
    @get:Rule
    val composeRule = createComposeRule()

    @Test
    fun `필터버튼이_선택되지_않은_상태`() {
        composeRule.setContent {
            FilterButton(
                value = "데이트",
                isSelected = false
            )
        }

        composeRule.onNodeWithText("데이트")
            .assertExists()
            .assertIsDisplayed()
    }

    @Test
    fun `필터버튼_선택한_상태`() {
        composeRule.setContent {
            FilterButton(
                value = "데이트",
                isSelected = true
            )
        }

        composeRule.onNodeWithText("데이트")
            .assertExists()
            .assertIsDisplayed()
    }
}