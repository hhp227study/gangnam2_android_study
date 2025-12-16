package com.survivalcoding.gangnam2kiandroidstudy.presentation.recipedetail

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.survivalcoding.gangnam2kiandroidstudy.domain.model.RecipeIngredient
import com.survivalcoding.gangnam2kiandroidstudy.presentation.component.IngredientCard

@Composable
fun IngredientScreen(ingredients: List<RecipeIngredient>) {
    LazyColumn(
        modifier = Modifier.fillMaxSize().padding(horizontal = 30.dp),
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        items(ingredients) { ingredient ->
            IngredientCard(ingredient)
        }
    }
}