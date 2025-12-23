package com.survivalcoding.gangnam2kiandroidstudy.data.datasource.remote

import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkRequest
import com.survivalcoding.gangnam2kiandroidstudy.data.datasource.NetworkDataSource
import com.survivalcoding.gangnam2kiandroidstudy.domain.model.NetworkStatus
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.distinctUntilChanged

class NetworkDataSourceImpl(
    private val connectivityManager: ConnectivityManager
) : NetworkDataSource {
    override val networkStatusFlow: Flow<NetworkStatus>
        get() = callbackFlow {
            val callback = object : ConnectivityManager.NetworkCallback() {
                override fun onAvailable(network: Network) {
                    trySend(NetworkStatus.Connected)
                }

                override fun onLost(network: Network) {
                    trySend(NetworkStatus.Disconnected)
                }
            }

            val request = NetworkRequest.Builder().build()
            connectivityManager.registerNetworkCallback(request, callback)

            // 초기 상태
            val isConnected = connectivityManager.activeNetwork != null
            trySend(if (isConnected) NetworkStatus.Connected else NetworkStatus.Disconnected)

            awaitClose {
                connectivityManager.unregisterNetworkCallback(callback)
            }
        }
            .distinctUntilChanged()
}