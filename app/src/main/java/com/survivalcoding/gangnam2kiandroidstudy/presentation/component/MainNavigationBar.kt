package com.survivalcoding.gangnam2kiandroidstudy.presentation.component

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.Fill
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation3.runtime.NavKey
import com.survivalcoding.gangnam2kiandroidstudy.R
import com.survivalcoding.gangnam2kiandroidstudy.core.routing.Route
import com.survivalcoding.gangnam2kiandroidstudy.ui.AppColors

@Composable
fun MainNavigationBar(
    modifier: Modifier = Modifier,
    currentRoute: NavKey?,
    onNavigate: (Route) -> Unit,
    onFabClick: () -> Unit = {},
) {
    val routes = listOf(Route.Home, Route.SavedRecipes, Route.Notification, Route.Profile)
    val icons = listOf(
        R.drawable.home_2,
        R.drawable.inactive,
        R.drawable.notification_bing,
        R.drawable.profile,
    )
    val selectedIcons = listOf(
        R.drawable.home_2,
        R.drawable.inactive,
        R.drawable.notification_bing,
        R.drawable.profile,
    )

    Box(
        modifier
            .background(AppColors.transparent)
            .fillMaxWidth()
            .height(86.dp)
    ) {

        // 배경 (곡선 형태)
        Canvas(
            modifier = Modifier
                .fillMaxWidth()
                .height(72.dp)
                .align(Alignment.BottomCenter)
        ) {
            val width = size.width
            val height = size.height
            val fabRadius = 24.dp.toPx()
            val path = Path().apply {
                moveTo(0f, 0f)
                lineTo(width * 0.41f, 0f)
                cubicTo(
                    width * 0.40f, 0f,
                    width * 0.43f, fabRadius * 1.6f,
                    width * 0.50f, fabRadius * 1.6f
                )
                cubicTo(
                    width * 0.57f, fabRadius * 1.6f,
                    width * 0.60f, 0f,
                    width * 0.59f, 0f
                )
                lineTo(width, 0f)
                lineTo(width, height)
                lineTo(0f, height)
                close()
            }

            drawPath(
                path = path,
                color = AppColors.white,
                style = Fill
            )
            drawPath(
                path = path,
                color = Color(0x14000000),
                style = Stroke(width = 1.dp.toPx())
            )
        }
        FloatingActionButton(
            onClick = onFabClick,
            shape = CircleShape,
            containerColor = AppColors.primary100,
            contentColor = AppColors.white,
            modifier = Modifier
                .size(48.dp)
                .align(Alignment.TopCenter)
            /*.offset(y = (-20).dp)*/,
            elevation = FloatingActionButtonDefaults.elevation(6.dp)
        ) {
            Icon(Icons.Default.Add, contentDescription = null, tint = Color.White)
        }

        // Navigation Icons (좌측 2개, 우측 2개)
        Row(
            Modifier
                .fillMaxWidth()
                .height(72.dp)
                .align(Alignment.BottomCenter),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Spacer(Modifier)
            routes.take(2).forEachIndexed { index, route ->
                val idx = index
                BottomItem(
                    iconResId = icons[idx],
                    selectedIconResId = selectedIcons[idx],
                    selected = currentRoute == route
                ) {
                    onNavigate(route)
                }
            }
            Spacer(Modifier.width(40.dp))
            routes.drop(2).forEachIndexed { index, route ->
                val idx = index + 2
                BottomItem(
                    iconResId = icons[idx],
                    selectedIconResId = selectedIcons[idx],
                    selected = currentRoute == route
                ) {
                    onNavigate(route)
                }
            }
            Spacer(Modifier)
        }
    }
}

@Composable
private fun BottomItem(iconResId: Int, selectedIconResId: Int, selected: Boolean, onClick: () -> Unit) {
    Box {
        Icon(
            painter = painterResource(if (selected) selectedIconResId else iconResId),
            contentDescription = null,
            modifier = Modifier.clickable(onClick = onClick),
            tint = if (selected) AppColors.primary100 else AppColors.gray4,
        )
    }
}

@Preview(showBackground = true)
@Composable
fun MainNavigationBarPreview() {
    MainNavigationBar(currentRoute = Route.Home, onNavigate = {})
}