package com.survivalcoding.gangnam2kiandroidstudy.data.datasource

import com.survivalcoding.gangnam2kiandroidstudy.domain.model.NetworkStatus
import kotlinx.coroutines.flow.Flow

interface NetworkDataSource {
    val networkStatusFlow: Flow<NetworkStatus>
}