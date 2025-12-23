package com.survivalcoding.gangnam2kiandroidstudy.domain.usecase

import com.survivalcoding.gangnam2kiandroidstudy.domain.model.NetworkStatus
import com.survivalcoding.gangnam2kiandroidstudy.domain.repository.NetworkRepository
import kotlinx.coroutines.flow.Flow

class ObserveNetworkStatusUseCase(
private val networkRepository: NetworkRepository
) {
    operator fun invoke(): Flow<NetworkStatus> {
        return networkRepository.observeNetwork()
    }
}