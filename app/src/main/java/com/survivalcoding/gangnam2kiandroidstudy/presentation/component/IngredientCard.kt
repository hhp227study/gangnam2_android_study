package com.survivalcoding.gangnam2kiandroidstudy.presentation.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.survivalcoding.gangnam2kiandroidstudy.R
import com.survivalcoding.gangnam2kiandroidstudy.data.model.RecipeIngredientUI
import com.survivalcoding.gangnam2kiandroidstudy.ui.AppColors
import com.survivalcoding.gangnam2kiandroidstudy.ui.AppTextStyles

@Composable
fun IngredientCard(
    ingredient: RecipeIngredientUI,
    modifier: Modifier = Modifier,
    imageLoader: @Composable (modifier: Modifier) -> Unit = { modifier ->
        AsyncImage(
            ingredient.image,
            modifier = modifier
                .size(width = 52.dp, height = 52.dp)
                .background(color = Color.White, shape = RoundedCornerShape(10.dp))
                .clip(RoundedCornerShape(10.dp)),
            contentDescription = ""
        )
    }
) {
    Card(
        modifier = modifier.fillMaxWidth().height(76.dp),
        shape = RoundedCornerShape(12.dp)
    ) {
        Row(
            modifier = Modifier.padding(horizontal = 15.dp, vertical = 12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            imageLoader(Modifier)
            Text(
                ingredient.name,
                fontWeight = FontWeight.SemiBold,
                modifier = Modifier.padding(start = 16.dp, top = 17.dp, bottom = 11.dp),
                style = AppTextStyles.normalTextRegular
            )
            Spacer(modifier = Modifier.weight(1f))
            Text(
                "${ingredient.amount}g",
                color = AppColors.gray3,
                style = AppTextStyles.smallTextRegular
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun IngredientCardPreview() {
    val ingredient = RecipeIngredientUI("Tomatos", 500, "https://cdn.pixabay.com/photo/2017/10/06/17/17/tomato-2823826_1280.jpg")

    IngredientCard(ingredient) { modifier ->
        Image(
            painter = painterResource(R.drawable.ic_launcher_background),
            modifier = modifier
                .size(width = 52.dp, height = 52.dp)
                .background(color = Color.White, shape = RoundedCornerShape(10.dp))
                .clip(RoundedCornerShape(10.dp)),
            contentDescription = ""
        )
    }
}