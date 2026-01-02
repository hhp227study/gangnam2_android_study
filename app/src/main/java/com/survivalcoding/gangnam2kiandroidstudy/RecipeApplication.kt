package com.survivalcoding.gangnam2kiandroidstudy

import android.app.Application
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.survivalcoding.gangnam2kiandroidstudy.core.di.authModule
import com.survivalcoding.gangnam2kiandroidstudy.core.di.clipboardModule
import com.survivalcoding.gangnam2kiandroidstudy.core.di.daoModule
import com.survivalcoding.gangnam2kiandroidstudy.core.di.dataModule
import com.survivalcoding.gangnam2kiandroidstudy.core.di.databaseModule
import com.survivalcoding.gangnam2kiandroidstudy.core.di.networkModule
import com.survivalcoding.gangnam2kiandroidstudy.core.di.repositoryModule
import com.survivalcoding.gangnam2kiandroidstudy.core.di.useCaseModule
import com.survivalcoding.gangnam2kiandroidstudy.core.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class RecipeApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@RecipeApplication)
            modules(
                viewModelModule,
                useCaseModule,
                repositoryModule,
                dataModule,
                networkModule,
                clipboardModule,
                databaseModule,
                daoModule,
                authModule
            )
        }
        if (BuildConfig.FLAVOR == "staging") {
            FirebaseFirestore.getInstance().useEmulator(
                "10.0.2.2",
                8080
            )
            FirebaseAuth.getInstance().useEmulator(
                "10.0.2.2",
                9099
            )
        }
    }
}