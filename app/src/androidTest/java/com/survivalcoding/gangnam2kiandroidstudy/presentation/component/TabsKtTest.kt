package com.survivalcoding.gangnam2kiandroidstudy.presentation.component

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class TabsKtTest {
    @get:Rule
    val composeRule = createComposeRule()

    @Test
    fun `탭을_클릭하면_선택된_인덱스가_변경된다`() {
        var selected by mutableStateOf(0)

        composeRule.setContent {
            Tabs(
                tabs = listOf("첫번째", "두번째", "세번째"),
                selectedIndex = selected,
                onTabSelected = { selected = it }
            )
        }

        composeRule.onNodeWithText("두번째").performClick()

        assert(selected == 1)

        composeRule.onNodeWithText("세번째").performClick()

        assert(selected == 2)
    }
}