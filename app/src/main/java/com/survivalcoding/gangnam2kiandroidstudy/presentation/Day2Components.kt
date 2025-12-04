package com.survivalcoding.gangnam2kiandroidstudy.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.survivalcoding.gangnam2kiandroidstudy.data.model.Recipe
import com.survivalcoding.gangnam2kiandroidstudy.data.model.RecipeIngredientUI
import com.survivalcoding.gangnam2kiandroidstudy.presentation.component.BigButton
import com.survivalcoding.gangnam2kiandroidstudy.presentation.component.FilterButton
import com.survivalcoding.gangnam2kiandroidstudy.presentation.component.IngredientCard
import com.survivalcoding.gangnam2kiandroidstudy.presentation.component.RateRecipeDialog
import com.survivalcoding.gangnam2kiandroidstudy.presentation.component.RatingButton
import com.survivalcoding.gangnam2kiandroidstudy.presentation.component.RecipeCard

@Composable
fun Day2Components() {
    var openDialog by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(30.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Column {
            IngredientCard(
                RecipeIngredientUI(
                    name = "Tomatoes",
                    amount = 500,
                    image = "https://cdn.pixabay.com/photo/2017/10/06/17/17/tomato-2823826_1280.jpg"
                )
            )
            Spacer(modifier = Modifier.height(36.dp))
            RecipeCard(
                Recipe(
                    0,
                    "",
                    "Traditional spare ribs baked",
                    "https://cdn.pixabay.com/photo/2017/11/10/15/04/steak-2936531_1280.jpg",
                    "Chef John",
                    "20 min",
                    4.0
                )
            )
            Spacer(modifier = Modifier.height(36.dp))
            Row(horizontalArrangement = Arrangement.spacedBy(15.dp)) {
                RatingButton(rating = 5, isSelected = false)
                RatingButton(rating = 4, isSelected = true)
                FilterButton(value = "Text", isSelected = false)
                FilterButton(value = "Text", isSelected = true)
            }
            Spacer(modifier = Modifier.height(36.dp))
            BigButton("Show Dialog") {
                openDialog = true
            }
            if (openDialog) {
                RateRecipeDialog(
                    "Rate recipe",
                    onDismiss = { openDialog = false },
                    onChange = { rating ->
                        openDialog = false
                    }
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun Day2ComponentsPreview() {
    Day2Components()
}
