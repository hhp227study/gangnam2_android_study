package com.survivalcoding.gangnam2kiandroidstudy.presentation.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.survivalcoding.gangnam2kiandroidstudy.R
import com.survivalcoding.gangnam2kiandroidstudy.domain.model.Recipe
import com.survivalcoding.gangnam2kiandroidstudy.ui.AppColors
import com.survivalcoding.gangnam2kiandroidstudy.ui.AppTextStyles

@Composable
fun NewRecipeCard(
    recipe: Recipe,
    modifier: Modifier = Modifier,
    onClick: (Int) -> Unit = {},
    imageLoader: @Composable BoxScope.(Modifier) -> Unit = { modifier ->
        AsyncImage(
            model = recipe.image,
            contentDescription = "Recipe image",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .padding(end = 9.3.dp)
                .size(86.dp)
                .clip(CircleShape)
                .align(Alignment.TopEnd)
        )
    }
) {
    Box(
        modifier = modifier
            .size(251.dp, 127.dp)
            .clip(RoundedCornerShape(10.dp))
            .clickable { onClick(recipe.id) },
        contentAlignment = Alignment.BottomCenter,
    ) {
        Box(
            modifier = Modifier
                .padding(horizontal = 1.dp, vertical = 1.dp)
                .size(251.dp, 95.dp)
                .shadow(
                    elevation = 8.dp,
                    shape = RoundedCornerShape(10.dp),
                    ambientColor = Color(0xFF696969),
                    spotColor = Color(0xFF696969),
                )
                .background(color = AppColors.white, shape = RoundedCornerShape(10.dp))
                .padding(horizontal = 9.3.dp, vertical = 10.dp),
        ) {
            Column {
                Text(
                    text = recipe.name,
                    style = AppTextStyles.smallTextRegular.copy(
                        fontWeight = FontWeight.SemiBold
                    ),
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier.padding(end = 102.26.dp),
                )

                Row(
                    modifier = Modifier.padding(top = 5.dp, bottom = 10.dp),
                    horizontalArrangement = Arrangement.spacedBy(2.dp),
                ) {
                    val rating = recipe.rating
                    val filledStars = rating.toInt()
                    val hasEmptyStar = rating % 1 != 0.0

                    repeat(filledStars) {
                        Image(
                            painter = painterResource(R.drawable.star_7),
                            contentDescription = "star ${it + 1}",
                            colorFilter = ColorFilter.tint(color = AppColors.rating),
                            modifier = Modifier.size(12.dp),
                        )
                    }

                    if (hasEmptyStar) {
                        Image(
                            painter = painterResource(R.drawable.star_8),
                            contentDescription = "empty star",
                            colorFilter = ColorFilter.tint(color = AppColors.rating),
                            modifier = Modifier.size(12.dp),
                        )
                    }
                }

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(8.dp),
                        verticalAlignment = Alignment.CenterVertically,
                    ) {
                        AsyncImage(
                            model = recipe.chef,
                            contentDescription = "recipe image",
                            modifier = Modifier
                                .size(25.dp)
                                .clip(CircleShape),
                            contentScale = ContentScale.Crop,
                        )
                        Text(
                            text = "By ${recipe.chef}",
                            style = AppTextStyles.smallerTextRegular,
                            color = AppColors.gray3,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis,
                        )
                    }

                    Row(
                        horizontalArrangement = Arrangement.spacedBy(5.dp),
                        verticalAlignment = Alignment.CenterVertically,
                    ) {
                        Image(
                            painter = painterResource(R.drawable.bookmark),
                            contentDescription = "timer image",
                            colorFilter = ColorFilter.tint(color = AppColors.gray4),
                            modifier = Modifier.size(17.dp),
                        )
                        Text(
                            text = "${recipe.time} min",
                            style = AppTextStyles.smallerTextRegular.copy(color = AppColors.gray4),
                        )
                    }
                }
            }
        }

        imageLoader(Modifier)
    }
}

@Preview
@Composable
private fun NewRecipeCardPreview() {
    val recipe = Recipe(
        id = 1,
        name = "spice roasted chicken with flavored rice".repeat(3),
        image = "https://cdn.pixabay.com/photo/2017/11/10/15/04/steak-2936531_1280.jpg",
        chef = "Chef John",
        time = "20",
        rating = 4.0,
    )
    NewRecipeCard(recipe = recipe, imageLoader = {
        Image(
            painter = painterResource(R.drawable.circle_recipe),
            contentDescription = "Recipe Image",
            modifier = Modifier
                .padding(end = 9.3.dp)
                .size(86.dp)
                .clip(CircleShape)
                .align(Alignment.TopEnd),
            contentScale = ContentScale.None
        )
    })
}

@Preview
@Composable
private fun HalfStarNewRecipeCardPreview() {
    val recipe = Recipe(
        id = 1,
        name = "spice roasted chicken with flavored rice".repeat(3),
        image = "https://cdn.pixabay.com/photo/2017/11/10/15/04/steak-2936531_1280.jpg",
        chef = "Chef John",
        time = "20",
        rating = 4.5,
    )
    NewRecipeCard(recipe = recipe, imageLoader = {
        Image(
            painter = painterResource(R.drawable.circle_recipe),
            contentDescription = "Recipe Image",
            modifier = Modifier
                .padding(end = 9.3.dp)
                .size(86.dp)
                .clip(CircleShape)
                .align(Alignment.TopEnd),
            contentScale = ContentScale.None
        )
    })
}