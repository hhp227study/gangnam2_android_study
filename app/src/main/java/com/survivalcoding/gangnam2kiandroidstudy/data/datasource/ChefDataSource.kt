package com.survivalcoding.gangnam2kiandroidstudy.data.datasource

import com.survivalcoding.gangnam2kiandroidstudy.domain.model.Chef

interface ChefDataSource {
    suspend fun getAllChefs(): List<Chef>
}