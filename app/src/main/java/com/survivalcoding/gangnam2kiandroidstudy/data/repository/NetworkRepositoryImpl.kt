package com.survivalcoding.gangnam2kiandroidstudy.data.repository

import com.survivalcoding.gangnam2kiandroidstudy.data.datasource.NetworkDataSource
import com.survivalcoding.gangnam2kiandroidstudy.domain.model.NetworkStatus
import com.survivalcoding.gangnam2kiandroidstudy.domain.repository.NetworkRepository
import kotlinx.coroutines.flow.Flow

class NetworkRepositoryImpl(
    private val networkDataSource: NetworkDataSource
) : NetworkRepository {
    override fun observeNetwork(): Flow<NetworkStatus> {
        return networkDataSource.networkStatusFlow
    }
}