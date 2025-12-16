package com.survivalcoding.gangnam2kiandroidstudy.presentation.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.unit.dp
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.survivalcoding.gangnam2kiandroidstudy.R
import com.survivalcoding.gangnam2kiandroidstudy.domain.model.RecipeIngredient
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class IngredientCardKtTest {

    @get:Rule
    val composeRule = createComposeRule()

    @Test
    fun `IngredientCard가_올바른_데이터를_표현하는지_테스트`() {
        val ingredient = RecipeIngredient(
            name = "Tomato",
            amount = 120,
            image = "https://test-image.com/tomato.png"
        )

        composeRule.setContent {
            IngredientCard(ingredient) { modifier ->
                Image(
                    painter = painterResource(R.drawable.ic_launcher_background),
                    modifier = modifier
                        .size(width = 52.dp, height = 52.dp)
                        .background(color = Color.White, shape = RoundedCornerShape(10.dp))
                        .clip(RoundedCornerShape(10.dp)),
                    contentDescription = ""
                )
            }
        }

        composeRule.onNodeWithText("Tomato")
            .assertExists()
            .assertIsDisplayed()

        composeRule.onNodeWithText("120g")
            .assertExists()
    }
}