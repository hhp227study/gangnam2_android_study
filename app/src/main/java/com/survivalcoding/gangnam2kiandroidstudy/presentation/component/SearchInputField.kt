package com.survivalcoding.gangnam2kiandroidstudy.presentation.component

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.survivalcoding.gangnam2kiandroidstudy.R
import com.survivalcoding.gangnam2kiandroidstudy.ui.AppColors
import com.survivalcoding.gangnam2kiandroidstudy.ui.AppTextStyles

@Composable
fun SearchInputField(
    value: String,
    modifier: Modifier = Modifier,
    onValueChange: (String) -> Unit,
    placeholder: String,
    enabled: Boolean = true,
    isOutlined: Boolean = true,
    focusedBorderColor: Color = AppColors.primary80,
    unfocusedBorderColor: Color = AppColors.gray4,
    onFocusChanged: (Boolean) -> Unit = {},
) {
    var isFocused by remember { mutableStateOf(false) }
    val focusModifier = Modifier.onFocusChanged { focusState ->
        isFocused = focusState.isFocused
        onFocusChanged(isFocused)
    }
    val shape = RoundedCornerShape(10.dp)
    val borderColor = when {
        !enabled -> AppColors.gray4
        isFocused -> focusedBorderColor
        else -> unfocusedBorderColor
    }
    val backgroundColor = when {
        !enabled -> AppColors.gray4
        else -> AppColors.white
    }

    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(40.dp)
            .background(backgroundColor, shape)
            .border(
                width = if (isOutlined) 1.dp else 0.dp,
                color = borderColor,
                shape = shape
            )
            .padding(horizontal = 10.dp)
            .clip(shape),
        contentAlignment = Alignment.CenterStart
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxSize()
        ) {
            Icon(
                painter = painterResource(R.drawable.search_normal),
                contentDescription = "검색 아이콘",
                tint = if (enabled) AppColors.gray4 else AppColors.gray2,
                modifier = Modifier.size(18.dp)
            )
            Box(modifier = Modifier.weight(1f).padding(start = 10.dp)) {
                if (value.isEmpty()) {
                    Text(
                        text = placeholder,
                        color = if (enabled) AppColors.gray4 else AppColors.gray2,
                        style = AppTextStyles.smallerTextRegular
                    )
                }
                BasicTextField(
                    value = value,
                    onValueChange = onValueChange,
                    enabled = enabled,
                    singleLine = true,
                    textStyle = AppTextStyles.smallerTextRegular.copy(
                        color = if (enabled) AppColors.black else AppColors.gray2
                    ),
                    cursorBrush = SolidColor(Color(0xFF4EBFA5)),
                    modifier = Modifier.fillMaxWidth().then(focusModifier)
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun SearchInputFieldPreview() {
    SearchInputField(value = "", onValueChange = {}, placeholder = "Placeholder", onFocusChanged = {})
}