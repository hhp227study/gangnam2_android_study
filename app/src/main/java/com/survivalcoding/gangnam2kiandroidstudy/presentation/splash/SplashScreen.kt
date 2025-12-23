package com.survivalcoding.gangnam2kiandroidstudy.presentation.splash

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.survivalcoding.gangnam2kiandroidstudy.R
import com.survivalcoding.gangnam2kiandroidstudy.presentation.component.MediumButton
import com.survivalcoding.gangnam2kiandroidstudy.ui.AppColors
import com.survivalcoding.gangnam2kiandroidstudy.ui.AppTextStyles

@Composable
fun SplashScreen(
    isStartEnabled: Boolean,
    onNavigate: () -> Unit
) {
    Box(
        modifier = Modifier.fillMaxSize().navigationBarsPadding(),
        contentAlignment = Alignment.Center
    ) {
        Image(
            painter = painterResource(id = R.drawable.background),
            contentDescription = null,
            modifier = Modifier.matchParentSize(),
            contentScale = ContentScale.Crop
        )
        Box(
            Modifier
                .matchParentSize()
                .background(
                    Brush.verticalGradient(
                        listOf(Color.Black.copy(alpha = 0.2f), Color.Black.copy(alpha = 1.0f))
                    )
                )
        )
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Spacer(modifier = Modifier.weight(104f))
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Image(
                    painter = painterResource(R.drawable.logo),
                    contentDescription = "Logo",
                    modifier = Modifier.padding(bottom = 14.dp).size(79.dp)
                )
                Text(
                    text = "100K+ Premium Recipe",
                    color = AppColors.white,
                    fontWeight = FontWeight.SemiBold,
                    style = AppTextStyles.mediumTextRegular
                )
            }
            Spacer(modifier = Modifier.weight(222f))
            Column(
                modifier = Modifier.padding(horizontal = 50.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Get Cooking",
                    modifier = Modifier.padding(horizontal = 31.dp),
                    color = AppColors.white,
                    fontWeight = FontWeight.SemiBold,
                    textAlign = TextAlign.Center,
                    lineHeight = 66.sp,
                    style = AppTextStyles.titleTextRegular
                )
                Text(
                    text = "Simple way to find Tasty Recipe",
                    modifier = Modifier.padding(top = 20.dp),
                    color = AppColors.white,
                    style = AppTextStyles.normalTextRegular
                )
            }
            Spacer(modifier = Modifier.weight(64f))
            MediumButton("Start Cooking", enabled = isStartEnabled, onClick = onNavigate)
            Spacer(modifier = Modifier.weight(84f))
        }
    }
}

@Preview(showBackground = true)
@Composable
fun SplashScreenPreview() {
    SplashScreen(
        isStartEnabled = true,
        onNavigate = fun() = Unit
    )
}