package com.survivalcoding.gangnam2kiandroidstudy.presentation.recipedetail

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.survivalcoding.gangnam2kiandroidstudy.R
import com.survivalcoding.gangnam2kiandroidstudy.domain.model.Recipe
import com.survivalcoding.gangnam2kiandroidstudy.domain.model.RecipeIngredient
import com.survivalcoding.gangnam2kiandroidstudy.presentation.component.AppBar
import com.survivalcoding.gangnam2kiandroidstudy.presentation.component.LinearRecipeCard
import com.survivalcoding.gangnam2kiandroidstudy.presentation.component.TabLayout
import com.survivalcoding.gangnam2kiandroidstudy.ui.AppColors
import com.survivalcoding.gangnam2kiandroidstudy.ui.AppTextStyles

@Composable
fun RecipeDetailScreen(
    recipeDetailUiState: RecipeDetailUiState,
    onAction: (RecipeDetailAction) -> Unit,
    onNavigateUp: () -> Unit
) {
    val tabs = listOf(
        "Ingredient" to @Composable { IngredientScreen(recipeDetailUiState.ingredients) },
        "Procedure" to @Composable { ProcedureScreen(recipeDetailUiState.procedures) }
    )

    Column(modifier = Modifier.fillMaxSize().navigationBarsPadding()) {
        AppBar(
            title = "",
            navigationIcon = {
                IconButton(onClick = onNavigateUp) {
                    Icon(painter = painterResource(R.drawable.arrow_left), contentDescription = null)
                }
            },
            actions = {
                IconButton(onClick = { /* Back action */ }) {
                    Icon(painter = painterResource(R.drawable.more), contentDescription = null)
                }
            }
        )
        Column(modifier = Modifier.fillMaxWidth().padding(horizontal = 30.dp)) {
            Box(modifier = Modifier.wrapContentSize(), contentAlignment = Alignment.Center) {
                LinearRecipeCard(recipe = recipeDetailUiState.recipe.copy(name = "", chef = ""))
                if (tabs[recipeDetailUiState.selectedTabPosition].first == "Procedure") {
                    Box(modifier = Modifier.size(48.dp).background(AppColors.gray4, CircleShape), contentAlignment = Alignment.Center) {
                        Icon(
                            painter = painterResource(R.drawable.play),
                            contentDescription = "재생",
                            modifier = Modifier.padding(start = 2.dp),
                            tint = AppColors.primary80
                        )
                    }
                }
            }
            Row(modifier = Modifier.padding(top = 10.dp).padding(horizontal = 5.dp)) {
                Text(
                    text = recipeDetailUiState.recipe.name,
                    modifier = Modifier.weight(1f),
                    maxLines = 2,
                    style = AppTextStyles.smallTextRegular.copy(
                        fontWeight = FontWeight.SemiBold
                    )
                )
                Spacer(modifier = Modifier.width(18.dp))
                Text(
                    text = "(13K Reviews)",
                    style = AppTextStyles.smallTextRegular.copy(
                        color = AppColors.gray3
                    )
                )
            }
        }
        Spacer(modifier = Modifier.height(10.dp))
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 30.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = rememberAsyncImagePainter(recipeDetailUiState.chef?.image),
                contentDescription = null,
                modifier = Modifier
                    .size(40.dp)
                    .clip(CircleShape)
            )
            Column(modifier = Modifier.padding(start = 10.dp)) {
                Text(
                    text = recipeDetailUiState.chef?.name ?: recipeDetailUiState.recipe.chef,
                    style = AppTextStyles.smallTextRegular.copy(
                        fontWeight = FontWeight.SemiBold
                    )
                )
                Row(modifier = Modifier.padding(top = 2.dp)) {
                    Icon(
                        painter = painterResource(R.drawable.location),
                        contentDescription = "지역",
                        modifier = Modifier.size(17.dp),
                        tint = AppColors.primary80
                    )
                    Spacer(modifier = Modifier.width(1.dp))
                    Text(
                        text = recipeDetailUiState.chef?.address ?: "",
                        style = AppTextStyles.smallerTextRegular
                    )
                }
            }
            Spacer(modifier = Modifier.weight(1f))
            Button(
                onClick = { onAction.invoke(RecipeDetailAction.FollowClick(0)) },
                modifier = Modifier.size(width = 85.dp, height = 37.dp),
                shape = RoundedCornerShape(10.dp),
                colors = ButtonColors(
                    containerColor = AppColors.primary100,
                    contentColor = AppColors.white,
                    disabledContainerColor = AppColors.gray4,
                    disabledContentColor = AppColors.white
                ),
                contentPadding = PaddingValues(10.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxSize(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    Box(
                        modifier = Modifier.height(17.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "Follow",
                            style = AppTextStyles.smallerTextRegular.copy(
                                color = AppColors.white,
                                fontWeight = FontWeight.SemiBold,
                            )
                        )
                    }
                }
            }
        }
        TabLayout(
            tabs = tabs.map { it.first },
            modifier = Modifier.padding(horizontal = 30.dp).padding(top = 12.dp, bottom = 13.dp),
            currentSelectTabPosition = recipeDetailUiState.selectedTabPosition,
            onTabClick = { onAction.invoke(RecipeDetailAction.TabClick(it)) }
        )
        Row(
            modifier = Modifier.padding(top = 22.dp, bottom = 20.dp).height(24.dp).fillMaxWidth().padding(horizontal = 30.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                painter = painterResource(R.drawable.ic_food_dish),
                contentDescription = null
            )
            Spacer(modifier = Modifier.width(5.dp))
            Text(
                text = "1 serve",
                style = AppTextStyles.smallerTextRegular.copy(
                    color = AppColors.gray3
                )
            )
            Spacer(modifier = Modifier.weight(1f))
            Text(
                text = if (tabs[recipeDetailUiState.selectedTabPosition].first == "Ingredient") "${recipeDetailUiState.ingredients.size} Items" else "${recipeDetailUiState.procedures.size} Steps",
                style = AppTextStyles.smallerTextRegular.copy(
                    color = AppColors.gray3
                )
            )
        }
        tabs[recipeDetailUiState.selectedTabPosition].second()
    }
}

@Preview(showBackground = true)
@Composable
fun RecipeDetailScreenPreview() {
    RecipeDetailScreen(
        recipeDetailUiState = RecipeDetailUiState(
            recipe = Recipe(
                id = 0,
                category = "category",
                name = "Spicy chicken burger with French fries",
                image = "image",
                chef = "Laura wilson",
                time = "time",
                rating = 0.0
            ),
            ingredients = listOf(
                RecipeIngredient("Tomatos", 500, ""),
                RecipeIngredient("Tomatos", 500, ""),
                RecipeIngredient("Tomatos", 500, ""),
                RecipeIngredient("Tomatos", 500, ""),
                RecipeIngredient("Tomatos", 500, "")
            ),
            procedures = emptyList(),
        ),
        onAction = fun (_) = Unit,
        onNavigateUp = fun() = Unit
    )
}