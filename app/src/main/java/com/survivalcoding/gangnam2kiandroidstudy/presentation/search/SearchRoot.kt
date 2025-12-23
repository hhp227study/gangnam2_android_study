package com.survivalcoding.gangnam2kiandroidstudy.presentation.search

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
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
    val snackbarHostState = remember { SnackbarHostState() }

    LaunchedEffect(Unit) {
        viewModel.event.collect { event ->
            when (event) {
                SearchEvent.ShowFilterBottomSheet -> {
                    sheetState.show()
                }
                SearchEvent.HideFilterBottomSheet -> {
                    sheetState.hide()
                }
                is SearchEvent.ShowMessage -> {
                    snackbarHostState.showSnackbar(event.message)
                }
                SearchEvent.NavigateBack -> {
                    onBackClick()
                }
            }
        }
    }

    Box(modifier = Modifier.fillMaxSize()) {
        SearchScreen(
            uiState = uiState,
            onAction = viewModel::onAction
        )
        SnackbarHost(
            hostState = snackbarHostState,
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .navigationBarsPadding()
        )
    }
    if (sheetState.isVisible) {
        ModalBottomSheet(
            sheetState = sheetState,
            onDismissRequest = {
                viewModel.onAction(SearchAction.FilterCancel)
            },
            shape = RoundedCornerShape(topStart = 50.dp, topEnd = 50.dp),
            containerColor = AppColors.white,
            dragHandle = null
        ) {
            FilterBottomSheet(
                filterSearchState = uiState.filterSearchState,
                onFilterClick = { time, rating, category ->
                    viewModel.onAction(
                        SearchAction.FilterApply(
                            FilterSearchState(time, rating, category)
                        )
                    )
                }
            )
        }
    }
}