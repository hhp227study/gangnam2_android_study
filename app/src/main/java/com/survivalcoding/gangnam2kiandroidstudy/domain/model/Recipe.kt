package com.survivalcoding.gangnam2kiandroidstudy.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class Recipe(
    val id: Int,
    val category: String = "",
    val name: String = "",
    val image: String = "",
    val chef: String = "",
    val time: String = "",
    val rating: Double = 0.0
)