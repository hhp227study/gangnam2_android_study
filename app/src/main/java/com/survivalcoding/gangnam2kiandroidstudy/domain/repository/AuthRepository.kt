package com.survivalcoding.gangnam2kiandroidstudy.domain.repository

import com.google.firebase.auth.FirebaseUser

interface AuthRepository {
    suspend fun signUpWithEmail(email: String, password: String)

    suspend fun signInWithEmail(email: String, password: String)

    suspend fun signInWithGoogle(idToken: String)

    suspend fun getCurrentUser(): FirebaseUser?

    suspend fun signOut()
}