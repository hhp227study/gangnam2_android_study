package com.survivalcoding.gangnam2kiandroidstudy.presentation.component

import androidx.compose.material.icons.Icons
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test

class BigButtonTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun myTest() {
        var counter = 0

        // Start the app
        composeTestRule.setContent {
            BigButton(
                "Button",
                onClick = {
                    counter++
                }
            )
        }

        composeTestRule.onNodeWithText("Button").performClick()

        assertEquals(1, counter)
    }

}