import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.survivalcoding.gangnam2kiandroidstudy.R
import com.survivalcoding.gangnam2kiandroidstudy.data.model.Recipe
import com.survivalcoding.gangnam2kiandroidstudy.ui.AppColors
import com.survivalcoding.gangnam2kiandroidstudy.ui.AppTextStyles

@Composable
fun RecipeCard(
    recipe: Recipe,
    modifier: Modifier = Modifier,
    imageLoader: @Composable (Modifier) -> Unit = { modifier ->
        AsyncImage(
            model = recipe.image,
            contentDescription = null,
            modifier = modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )
    }
) {
    Box(
        modifier = modifier
            .height(150.dp)
            .clip(RoundedCornerShape(10.dp))
    ) {
        imageLoader(Modifier)
        Row(
            modifier = Modifier
                .align(Alignment.TopEnd)
                .padding(10.dp)
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
                text = recipe.raring.toString(),
                style = AppTextStyles.smallerTextRegular,
                color = Color.Black
            )
        }
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.BottomCenter)
                .height(90.dp)
                .background(
                    Brush.verticalGradient(
                        colors = listOf(Color.Transparent, Color.Black.copy(alpha = 0.8f))
                    )
                )
        )
        Column(
            modifier = Modifier
                .align(Alignment.BottomStart)
                .padding(start = 10.dp, bottom = 10.dp)
                .height(54.dp),
            verticalArrangement = Arrangement.Bottom
        ) {
            Text(
                recipe.name,
                modifier = Modifier,
                color = AppColors.white,
                fontWeight = FontWeight.SemiBold,
                style = AppTextStyles.smallTextRegular,
                maxLines = 2
            )
            Text(
                recipe.chef,
                modifier = Modifier,
                color = AppColors.white,
                style = AppTextStyles.smallerTextRegular,
                maxLines = 1
            )
        }
        Row(
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(end = 44.dp, bottom = 10.dp)
                .height(24.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                painter = painterResource(R.drawable.timer),
                contentDescription = null,
                tint = Color.White,
                modifier = Modifier.size(17.dp)
            )
            Spacer(modifier = Modifier.width(5.dp))
            Text(
                text = recipe.time,
                color = Color.White,
                style = AppTextStyles.smallerTextRegular
            )
        }
        Box(
            modifier = Modifier
                .padding(10.dp)
                .size(24.dp)
                .background(color = AppColors.white, shape = CircleShape)
                .align(Alignment.BottomEnd)
        ) {
            Icon(
                painter = painterResource(R.drawable.bookmark),
                contentDescription = null,
                modifier = Modifier
                    .align(Alignment.Center),
                tint = AppColors.primary100
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun RecipeCardPreview() {
    val recipe = Recipe(
        0,
        "",
        "Traditional spare ribs baked",
        "https://cdn.pixabay.com/photo/2017/11/10/15/04/steak-2936531_1280.jpg",
        "By Chef John",
        "20 min",
        4.0
    )

    Box(Modifier.width(375.dp).padding(30.dp)) {
        RecipeCard(recipe) { modifier ->
            Image(
                painter = painterResource(R.drawable.ic_launcher_background),
                contentDescription = "item",
                modifier = modifier.fillMaxSize(),
                contentScale = ContentScale.Crop
            )
        }
    }
}
