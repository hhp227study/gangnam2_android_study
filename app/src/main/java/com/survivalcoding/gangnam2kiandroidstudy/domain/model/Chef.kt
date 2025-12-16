package com.survivalcoding.gangnam2kiandroidstudy.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class Chef(
    val id: Int,
    val name: String,
    val image: String,
    val address: String
)