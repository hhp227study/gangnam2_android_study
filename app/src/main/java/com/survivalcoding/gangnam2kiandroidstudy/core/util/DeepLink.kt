package com.survivalcoding.gangnam2kiandroidstudy.core.util

import android.net.Uri
import com.survivalcoding.gangnam2kiandroidstudy.core.routing.Route

object DeepLink {
    fun parseRecipeDeepLink(uri: Uri?): List<Route>? {
        if (uri == null) return null
        if (uri.scheme != "https") return null
        if (uri.host != "gangnam2kiandroidstudy.web.app") return null

        val segments = uri.pathSegments
        if (segments.isEmpty()) return null

        return when {
            // /recipes/saved
            segments.size == 2 &&
                    segments[0] == "recipes" &&
                    segments[1] == "saved" -> {
                listOf(
                    Route.Main,
                    Route.SavedRecipes
                )
            }

            // /recipes/{recipeId}
            segments.size == 2 &&
                    segments[0] == "recipes" -> {
                val recipeId = segments[1].toIntOrNull() ?: return null
                listOf(
                    Route.Main,
                    Route.RecipeDetails(recipeId)
                )
            }

            else -> null
        }
    }
}