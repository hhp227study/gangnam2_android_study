package com.survivalcoding.gangnam2kiandroidstudy.presentation.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.survivalcoding.gangnam2kiandroidstudy.R
import com.survivalcoding.gangnam2kiandroidstudy.domain.model.Recipe
import com.survivalcoding.gangnam2kiandroidstudy.ui.AppColors
import com.survivalcoding.gangnam2kiandroidstudy.ui.AppTextStyles

@Composable
fun HorizontalRecipeCard(
    recipe: Recipe,
    modifier: Modifier = Modifier,
    onBookmarkClick: () -> Unit,
    imageLoader: @Composable BoxScope.(Modifier) -> Unit = { modifier ->
        AsyncImage(
            model = recipe.image,
            contentDescription = "Recipe Image",
            modifier = Modifier
                .size(110.dp)
                .clip(CircleShape)
                .align(Alignment.TopCenter),
            contentScale = ContentScale.None
        )
    }
) {
    Box(
        modifier = modifier.size(width = 150.dp, height = 231.dp),
    ) {
        Box(
            modifier = Modifier
                .size(width = 150.dp, height = 176.dp)
                .background(color = AppColors.gray4, shape = RoundedCornerShape(12.dp))
                .padding(10.dp)
                .align(Alignment.BottomCenter)
        ) {
            Text(
                text = recipe.name,
                modifier = Modifier.align(Alignment.Center),
                style = AppTextStyles.smallTextRegular.copy(
                    fontWeight = FontWeight.SemiBold,
                    textAlign = TextAlign.Center
                )
            )
            Column(
                modifier = Modifier.align(Alignment.BottomStart)
            ) {
                Text(
                    text = "Time",
                    style = AppTextStyles.smallerTextRegular
                        .copy(color = AppColors.gray3)
                )
                Spacer(modifier = Modifier.height(5.dp))
                Text(
                    text = recipe.time,
                    style = AppTextStyles.smallerTextRegular.copy(
                        fontWeight = FontWeight.SemiBold
                    )
                )
            }
            Box(
                modifier = Modifier
                    .size(24.dp)
                    .background(color = AppColors.white, shape = CircleShape)
                    .align(Alignment.BottomEnd)
                    .clickable(onClick = onBookmarkClick)
            ) {
                Icon(
                    painter = painterResource(R.drawable.bookmark),
                    contentDescription = null,
                    modifier = Modifier.align(Alignment.Center),
                    tint = if (recipe.isBookmarked) AppColors.primary100 else AppColors.gray3
                )
            }
        }
        Box(modifier = Modifier.fillMaxWidth()) {
            imageLoader(Modifier)
            Row(
                modifier = Modifier
                    .align(Alignment.TopEnd)
                    .padding(top = 30.dp)
                    .background(AppColors.secondary20, RoundedCornerShape(20.dp))
                    .padding(horizontal = 7.dp, vertical = 2.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    painter = painterResource(R.drawable.star),
                    contentDescription = null,
                    tint = AppColors.rating
                )
                Spacer(modifier = Modifier.width(3.dp))
                Text(
                    text = recipe.rating.toString(),
                    style = AppTextStyles.smallerTextRegular,
                    color = Color.Black
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun HorizontalRecipeCardPreview() {
    HorizontalRecipeCard(
        Recipe(
            id = 0,
            category = "All",
            name = "Classic Greek Salad",
            image = "https://cdn.pixabay.com/photo/2014/11/05/15/57/salmon-518032_1280.jpg",
            chef = "unknown",
            time = "15 min",
            rating = 4.5
        ),
        modifier = Modifier.padding(10.dp),
        onBookmarkClick = {}
    ) { modifier ->
        Image(
            painter = painterResource(R.drawable.circle_recipe),
            contentDescription = "Recipe Image",
            modifier = Modifier
                .size(110.dp)
                .clip(CircleShape)
                .align(Alignment.TopCenter),
            contentScale = ContentScale.None
        )
    }
}