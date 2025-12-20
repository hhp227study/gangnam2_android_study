package com.survivalcoding.gangnam2kiandroidstudy.presentation.search

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.survivalcoding.gangnam2kiandroidstudy.presentation.component.FilterBottomSheet
import com.survivalcoding.gangnam2kiandroidstudy.ui.AppColors
import org.koin.androidx.compose.koinViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchRoot(
    viewModel: SearchViewModel = koinViewModel(),
    onBackClick: () -> Unit
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val sheetState = rememberModalBottomSheetState(
        skipPartiallyExpanded = true
    )

    SearchScreen(
        uiState,
        viewModel::onAction,
        onBackClick
    )
    if (uiState.isShowBottomSheet) {
        ModalBottomSheet(
            sheetState = sheetState,
            onDismissRequest = { viewModel.onAction(SearchAction.FilterButtonClick(false)) },
            shape = RoundedCornerShape(topStart = 50.dp, topEnd = 50.dp),
            containerColor = AppColors.white,
            dragHandle = null
        ) {
            FilterBottomSheet(
                filterSearchState = uiState.filterSearchState,
                onFilterClick = { time, rating, category ->
                    val filterResult = FilterSearchState(time, rating, category)

                    viewModel.onAction(SearchAction.FilterApply(filterResult))
                }
            )
        }
    }
}