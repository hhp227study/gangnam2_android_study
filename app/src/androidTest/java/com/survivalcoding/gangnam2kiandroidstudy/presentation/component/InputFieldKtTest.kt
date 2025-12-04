package com.survivalcoding.gangnam2kiandroidstudy.presentation.component

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onAllNodesWithText
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performTextInput
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Assert.*
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class InputFieldKtTest {
    @get:Rule
    val composeRule = createComposeRule()

    @Test
    fun `입력필드_텍스트가_정상적으로_변경된다`() {
        var text by mutableStateOf("")

        composeRule.setContent {
            InputField(
                label = "Label",
                value = text,
                onValueChange = { text = it },
                placeholder = "Placeholder"
            )
        }

        val inputText = "Hello World"

        composeRule
            .onNodeWithText("Placeholder")
            .performTextInput(inputText)

        assert(text == inputText)
    }
}