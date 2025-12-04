package com.survivalcoding.gangnam2kiandroidstudy.presentation.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.survivalcoding.gangnam2kiandroidstudy.ui.AppColors
import com.survivalcoding.gangnam2kiandroidstudy.ui.AppTextStyles

@Composable
fun Tabs(
    tabs: List<String>,
    selectedIndex: Int,
    onTabSelected: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    val tabSpacing = when (tabs.size) {
        2 -> 15.dp
        3 -> 7.dp
        else -> (20f / tabs.size).dp
    }

    Row(
        modifier = modifier
            .size(width = 375.dp, height = 58.dp)
            .padding(horizontal = (60f / tabs.size).dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        tabs.forEachIndexed { index, label ->
            Box(
                modifier = Modifier
                    .weight(1f)
                    .height(33.dp)
                    .clip(RoundedCornerShape(10.dp))
                    .background(if (index == selectedIndex) AppColors.primary100 else Color.Transparent)
                    .clickable { onTabSelected(index) },
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = label,
                    color = if (index == selectedIndex) Color.White else AppColors.primary80,
                    fontWeight = FontWeight.SemiBold,
                    style = AppTextStyles.smallTextRegular2
                )
            }
            if (index != tabs.lastIndex) {
                Spacer(modifier = Modifier.width(tabSpacing))
            }
        }
    }
}