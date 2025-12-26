package com.survivalcoding.gangnam2kiandroidstudy.core.util

import android.net.Uri
import com.survivalcoding.gangnam2kiandroidstudy.core.routing.DeepLinkTarget
import com.survivalcoding.gangnam2kiandroidstudy.core.routing.Route

object DeepLink {
    fun parseRecipeDeepLink(uri: Uri?): DeepLinkTarget? {
        if (uri == null) return null
        if (uri.scheme != "https") return null

        val segments = uri.pathSegments

        return when {
            segments == listOf("recipes", "saved") ->
                DeepLinkTarget.SavedRecipes

            segments.size == 2 && segments[0] == "recipes" ->
                segments[1].toIntOrNull()?.let {
                    DeepLinkTarget.RecipeDetail(it)
                }

            else -> null
        }
    }

}