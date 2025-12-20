package com.survivalcoding.gangnam2kiandroidstudy.presentation.search

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.survivalcoding.gangnam2kiandroidstudy.R
import com.survivalcoding.gangnam2kiandroidstudy.presentation.component.AppBar
import com.survivalcoding.gangnam2kiandroidstudy.presentation.component.GridRecipeCard
import com.survivalcoding.gangnam2kiandroidstudy.presentation.component.SearchInputField
import com.survivalcoding.gangnam2kiandroidstudy.ui.AppColors
import com.survivalcoding.gangnam2kiandroidstudy.ui.AppTextStyles

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchScreen(
    uiState: SearchUiState,
    onAction: (SearchAction) -> Unit,
    onBackClick: () -> Unit
) {
    Column(modifier = Modifier.fillMaxSize().navigationBarsPadding()) {
        AppBar(
            title = "Search recipes",
            navigationIcon = {
                Icon(
                    painter = painterResource(R.drawable.arrow_left),
                    contentDescription = "뒤로가기",
                    modifier = Modifier.size(20.dp).clickable(onClick = onBackClick)
                )
            }
        )
        Box(modifier = Modifier.fillMaxSize()) {
            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                contentPadding = PaddingValues(horizontal = 30.dp),
                verticalArrangement = Arrangement.spacedBy(15.dp),
                horizontalArrangement = Arrangement.spacedBy(15.dp),
            ) {
                item(span = { GridItemSpan(2) }) {
                    GridHeader(
                        searchKeyword = uiState.searchKeyword,
                        onSearchKeywordChange = { onAction.invoke(SearchAction.SearchKeywordChange(it)) },
                        onFilterButtonClick = { onAction.invoke(SearchAction.FilterButtonClick(true)) },
                        filteredRecipesSize = uiState.filteredRecipes.size
                    )
                }
                if (uiState.searchKeyword.isEmpty()) {
                    items(uiState.allRecipes) { recipe ->
                        GridRecipeCard(recipe)
                    }
                } else {
                    items(uiState.filteredRecipes) { recipe ->
                        GridRecipeCard(recipe)
                    }
                }
            }
        }
    }
}

@Composable
fun GridHeader(
    searchKeyword: String,
    onSearchKeywordChange: (String) -> Unit,
    onFilterButtonClick: () -> Unit,
    filteredRecipesSize: Int = 0,
) {
    Column {
        Row(
            modifier = Modifier.padding(top = 7.dp).fillMaxWidth().height(40.dp)
        ) {
            SearchInputField(
                value = searchKeyword,
                modifier = Modifier.weight(1f),
                onValueChange = onSearchKeywordChange,
                placeholder = "Search recipe"
            )
            Spacer(modifier = Modifier.width(20.dp))
            Button(
                onClick = onFilterButtonClick,
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
        Spacer(modifier = Modifier.height(20.dp))
        Row(
            modifier = Modifier.padding(bottom = 5.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = if (!searchKeyword.isEmpty()) "Search Result" else "Recent Search",
                style = AppTextStyles.normalTextRegular.copy(fontWeight = FontWeight.SemiBold)
            )
            Spacer(modifier = Modifier.weight(1f))
            if (!searchKeyword.isEmpty()) {
                Text(
                    text = "$filteredRecipesSize results",
                    style = AppTextStyles.smallerTextRegular.copy(color = AppColors.gray3)
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview(showBackground = true)
@Composable
fun SearchScreenPreview() {
    SearchScreen(
        uiState = SearchUiState(),
        onAction = {},
        onBackClick = {}
    )
}