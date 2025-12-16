package com.survivalcoding.gangnam2kiandroidstudy.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class IngredientEntity(
    val id: Int = 0,
    val name: String = "",
    val image: String = ""
)