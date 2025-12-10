package com.survivalcoding.gangnam2kiandroidstudy.data

import com.survivalcoding.gangnam2kiandroidstudy.data.datasource.AppAssetManager
import java.io.ByteArrayInputStream
import java.io.InputStream

class MockAssetManager(private val jsonString: String) : AppAssetManager {
    override fun open(fileName: String): InputStream {
        return ByteArrayInputStream(jsonString.toByteArray())
    }
}