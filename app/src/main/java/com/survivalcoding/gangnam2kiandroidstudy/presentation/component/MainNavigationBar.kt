package com.survivalcoding.gangnam2kiandroidstudy.presentation.component

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation3.runtime.NavKey
import com.survivalcoding.gangnam2kiandroidstudy.R
import com.survivalcoding.gangnam2kiandroidstudy.core.routing.Route
import com.survivalcoding.gangnam2kiandroidstudy.ui.AppColors

@Composable
private fun MainNavigationBar(
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
    val labels = listOf("Home", "Saved Recipes", "Notification", "Profile")

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(86.dp),
        contentAlignment = Alignment.BottomCenter,
    ) {
        NavigationBar(
            containerColor = AppColors.white,
            modifier = Modifier.height(72.dp),
        ) {
            routes.take(2).forEachIndexed { index, route ->
                NavigationBarItem(
                    selected = currentRoute == route,
                    onClick = { onNavigate(route) },
                    icon = {
                        Icon(
                            painter = painterResource(if (currentRoute == route) selectedIcons[index] else icons[index]),
                            contentDescription = labels[index],
                            tint = if (currentRoute == route) AppColors.primary100 else AppColors.gray4,
                        )
                    },
                    colors = NavigationBarItemDefaults.colors(
                        indicatorColor = AppColors.white,
                    ),
                )
            }
            Spacer(modifier = Modifier.weight(1f))
            routes.takeLast(2).forEachIndexed { index, route ->
                val realIndex = index + 2
                NavigationBarItem(
                    selected = currentRoute == route,
                    onClick = { onNavigate(route) },
                    icon = {
                        Icon(
                            painter = painterResource(if (currentRoute == route) selectedIcons[realIndex] else icons[realIndex]),
                            contentDescription = labels[realIndex],
                            tint = if (currentRoute == route) AppColors.transparent else AppColors.gray4,
                        )
                    },
                    colors = NavigationBarItemDefaults.colors(
                        indicatorColor = AppColors.white,
                    ),
                )
            }
        }
        FloatingActionButton(
            modifier = Modifier
                .align(Alignment.TopCenter)
                .size(48.dp),
            onClick = onFabClick,
            shape = CircleShape,
            containerColor = AppColors.primary100,
            contentColor = AppColors.white,
        ) {
            Icon(
                painter = painterResource(R.drawable.plus),
                contentDescription = "Add",
                modifier = Modifier.size(21.dp),
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MainNavigationBarPreview() {
    MainNavigationBar(Route.Home, {})
}