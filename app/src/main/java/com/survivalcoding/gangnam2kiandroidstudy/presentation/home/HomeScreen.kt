package com.survivalcoding.gangnam2kiandroidstudy.presentation.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.survivalcoding.gangnam2kiandroidstudy.R
import com.survivalcoding.gangnam2kiandroidstudy.presentation.component.HorizontalRecipeCard
import com.survivalcoding.gangnam2kiandroidstudy.presentation.component.RecipeCategorySelector
import com.survivalcoding.gangnam2kiandroidstudy.presentation.component.SearchInputField
import com.survivalcoding.gangnam2kiandroidstudy.ui.AppColors
import com.survivalcoding.gangnam2kiandroidstudy.ui.AppTextStyles

@Composable
fun HomeScreen(
    uiState: HomeUiState,
    onSearchKeywordChange: (String) -> Unit,
    onSelectCategory: (String) -> Unit,
    onSearchKeywordFocusChanged: (Boolean) -> Unit
) {
    Column(modifier = Modifier.fillMaxSize().navigationBarsPadding()) {
        Spacer(modifier = Modifier.height(54.dp))
        Row(
            modifier = Modifier.padding(horizontal = 30.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(Modifier.wrapContentWidth()) {
                Text(
                    text = "Hello Jega",
                    style = AppTextStyles.largeTextRegular.copy(
                        fontWeight = FontWeight.SemiBold
                    ),
                    maxLines = 1
                )
                Text(
                    text = "What are you cooking today?",
                    modifier = Modifier.padding(top = 5.dp),
                    style = AppTextStyles.smallerTextRegular.copy(
                        fontWeight = FontWeight.Medium,
                        color = AppColors.gray3
                    )
                )
            }
            Spacer(modifier = Modifier.weight(1f))
            Image(
                painter = painterResource(R.drawable.avatar),
                contentDescription = "아바타",
                modifier = Modifier.size(40.dp)
            )
        }
        Row(
            modifier = Modifier.padding(top = 30.dp, start = 30.dp, end = 30.dp).fillMaxWidth().height(40.dp)
        ) {
            SearchInputField(
                value = uiState.searchKeyword,
                modifier = Modifier.weight(1f),
                onValueChange = onSearchKeywordChange,
                placeholder = "Search recipe",
                onFocusChanged = onSearchKeywordFocusChanged
            )
            Spacer(modifier = Modifier.width(20.dp))
            Button(
                onClick = {},
                modifier = Modifier.size(40.dp),
                shape = RoundedCornerShape(10.dp),
                colors = ButtonColors(
                    containerColor = AppColors.primary100,
                    contentColor = AppColors.white,
                    disabledContainerColor = AppColors.gray4,
                    disabledContentColor = AppColors.white
                ),
                contentPadding = PaddingValues(0.dp)
            ) {
                Icon(
                    painter = painterResource(R.drawable.setting_4),
                    contentDescription = "필터 아이콘",
                    modifier = Modifier.size(20.dp)
                )
            }
        }
        RecipeCategorySelector(
            items = listOf("All", "Indian", "Italian", "Asian", "Chinese", "Fruit", "Vegetables", "Protein", "Cereal", "Local Dishes"),
            modifier = Modifier.padding(top = 15.dp),
            contentPadding = PaddingValues(start = 30.dp),
            selectedCategory = uiState.selectedCategory,
            onCategoryClick = onSelectCategory
        )
        LazyRow(
            modifier = Modifier.padding(top = 15.dp),
            contentPadding = PaddingValues(horizontal = 30.dp),
            horizontalArrangement = Arrangement.spacedBy(15.dp)
        ) {
            items(uiState.recipes) { recipe ->
                HorizontalRecipeCard(recipe)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    HomeScreen(
        uiState = HomeUiState(),
        onSearchKeywordChange = {},
        onSelectCategory = {},
        onSearchKeywordFocusChanged = {}
    )
}