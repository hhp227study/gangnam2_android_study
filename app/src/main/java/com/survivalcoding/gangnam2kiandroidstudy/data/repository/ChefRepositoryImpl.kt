package com.survivalcoding.gangnam2kiandroidstudy.data.repository

import com.survivalcoding.gangnam2kiandroidstudy.core.util.suspendRunCatching
import com.survivalcoding.gangnam2kiandroidstudy.data.datasource.ChefDataSource
import com.survivalcoding.gangnam2kiandroidstudy.domain.model.Chef
import com.survivalcoding.gangnam2kiandroidstudy.domain.repository.ChefRepository

class ChefRepositoryImpl private constructor(
    private val chefDataSource: ChefDataSource
) : ChefRepository {
    override suspend fun getChefByName(name: String): Result<Chef> {
        return suspendRunCatching {
            chefDataSource.getAllChefs().find { it.name == name } ?: throw Exception("요리사를 찾을수 없습니다.")
        }
    }

    companion object {
        @Volatile private var instance: ChefRepository? = null

        fun getInstance(chefDataSource: ChefDataSource) =
            instance ?: synchronized(this) {
                instance ?: ChefRepositoryImpl(chefDataSource).also { instance = it }
            }
    }
}