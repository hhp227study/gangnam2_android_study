package com.survivalcoding.gangnam2kiandroidstudy.presentation.component

import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.survivalcoding.gangnam2kiandroidstudy.R
import com.survivalcoding.gangnam2kiandroidstudy.ui.AppColors
import com.survivalcoding.gangnam2kiandroidstudy.ui.AppTextStyles

@Composable
fun MediumButton(
    text: String,
    modifier: Modifier = Modifier,
    onClick: () -> Unit = {},
) {
    val interactionSource = remember { MutableInteractionSource() }
    val isPressed by interactionSource.collectIsPressedAsState()

    Button(
        onClick = onClick,
        modifier = modifier.size(width = 243.dp, height = 54.dp),
        shape = RoundedCornerShape(10.dp),
        colors = ButtonColors(
            containerColor = if (isPressed) AppColors.gray4 else AppColors.primary100,
            contentColor = AppColors.white,
            disabledContainerColor = AppColors.gray4,
            disabledContentColor = AppColors.white
        ),
        interactionSource = interactionSource
    ) {
        Row(
            modifier = Modifier.fillMaxSize(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Box(
                modifier = Modifier.size(width = 114.dp, height = 24.dp),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = text,
                    fontWeight = FontWeight.SemiBold,
                    style = AppTextStyles.normalTextRegular.copy(color = AppColors.white),
                )
            }
            Spacer(modifier = Modifier.width(9.dp))
            Icon(
                painter = painterResource(R.drawable.arrow_right),
                tint = AppColors.white,
                contentDescription = "오른쪽 화살표"
            )
        }
    }
}