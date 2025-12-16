package com.survivalcoding.gangnam2kiandroidstudy.domain.repository

import com.survivalcoding.gangnam2kiandroidstudy.domain.model.Procedure

interface ProcedureRepository {
    suspend fun getProcedureByRecipeId(recipeId: Int): Result<List<Procedure>>
}