package com.survivalcoding.gangnam2kiandroidstudy.data.datasource

import com.google.firebase.auth.FirebaseUser

interface AuthDataSource {
    suspend fun signUpWithEmail(email: String, password: String)

    suspend fun signInWithEmail(email: String, password: String)

    suspend fun signInWithGoogle(idToken: String)

    suspend fun getCurrentUser(): FirebaseUser?

    suspend fun signOut()
}