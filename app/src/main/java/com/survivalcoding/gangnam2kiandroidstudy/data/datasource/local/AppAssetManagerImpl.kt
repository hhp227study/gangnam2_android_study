package com.survivalcoding.gangnam2kiandroidstudy.data.datasource.local

import android.content.res.AssetManager
import com.survivalcoding.gangnam2kiandroidstudy.data.datasource.AppAssetManager

class AppAssetManagerImpl(private val assetManager: AssetManager) : AppAssetManager {
    override fun open(fileName: String) = assetManager.open(fileName)
}