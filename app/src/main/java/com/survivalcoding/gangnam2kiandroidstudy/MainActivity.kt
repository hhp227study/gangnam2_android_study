package com.survivalcoding.gangnam2kiandroidstudy

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.survivalcoding.gangnam2kiandroidstudy.core.routing.NavigationRoot

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val deepLinkUri = intent?.data

        enableEdgeToEdge()
        setContent {
            NavigationRoot(deepLinkUri = deepLinkUri)
        }
    }

    override fun onNewIntent(intent: Intent) {
        super.onNewIntent(intent)
        setIntent(intent)
    }
}