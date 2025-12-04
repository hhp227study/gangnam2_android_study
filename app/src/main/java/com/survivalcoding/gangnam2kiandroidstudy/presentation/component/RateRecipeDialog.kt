package com.survivalcoding.gangnam2kiandroidstudy.presentation.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.survivalcoding.gangnam2kiandroidstudy.R
import com.survivalcoding.gangnam2kiandroidstudy.ui.AppColors
import com.survivalcoding.gangnam2kiandroidstudy.ui.AppTextStyles

@Composable
fun RateRecipeDialog(
    title: String,
    onDismiss: () -> Unit,
    onChange: (Int) -> Unit,
    starFilled: Painter = painterResource(R.drawable.star_7),
    starEmpty: Painter = painterResource(R.drawable.star_8)
) {
    var rating by remember { mutableStateOf(0) }

    Dialog(onDismissRequest = onDismiss) {
        Card(
            modifier = Modifier
                .padding(20.dp)
                .clip(RoundedCornerShape(8.dp))
                .height(91.dp)
        ) {
            Column(
                modifier = Modifier
                    .padding(vertical = 10.dp, horizontal = 15.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = title,
                    fontWeight = FontWeight.Medium,
                    color = Color.Black,
                    style = AppTextStyles.smallerTextRegular
                )
                Spacer(modifier = Modifier.height(5.dp))
                Row(horizontalArrangement = Arrangement.spacedBy(10.dp)) {
                    for (i in 1..5) {
                        Image(
                            painter = if (i <= rating) starFilled else starEmpty,
                            contentDescription = null,
                            modifier = Modifier
                                .size(20.dp)
                                .padding(vertical = 2.dp)
                                .clickable { rating = i }
                        )
                    }
                }
                Spacer(modifier = Modifier.height(5.dp))
                Button(
                    onClick = { onChange(rating) },
                    modifier = Modifier.size(width = 61.dp, height = 20.dp),
                    shape = RoundedCornerShape(6.dp),
                    contentPadding = PaddingValues(
                        horizontal = 10.dp,
                        vertical = 4.dp
                    ),
                    enabled = rating > 0,
                    colors = ButtonColors(
                        containerColor = AppColors.rating,
                        contentColor = AppColors.rating,
                        disabledContainerColor = Color.LightGray,
                        disabledContentColor = Color.White
                    )
                ) {
                    Text(
                        text = "Send",
                        color = AppColors.white,
                        style = AppTextStyles.smallerTextSmall
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun RateRecipeDialogPreview() {
    RateRecipeDialog("Rate recipe", fun() = Unit, fun(_) = Unit)
}
