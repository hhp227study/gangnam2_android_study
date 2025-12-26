package com.survivalcoding.gangnam2kiandroidstudy.core.routing

sealed interface DeepLinkTarget {
    data object SavedRecipes : DeepLinkTarget
    data class RecipeDetail(val id: Int) : DeepLinkTarget
}
