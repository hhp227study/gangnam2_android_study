package com.survivalcoding.gangnam2kiandroidstudy.data.repository

import com.google.firebase.auth.FirebaseUser
import com.survivalcoding.gangnam2kiandroidstudy.data.datasource.AuthDataSource
import com.survivalcoding.gangnam2kiandroidstudy.domain.repository.AuthRepository

class AuthRepositoryImpl(
    private val authDataSource: AuthDataSource
) : AuthRepository {
    override suspend fun signUpWithEmail(email: String, password: String) {
        return authDataSource.signUpWithEmail(email, password)
    }

    override suspend fun signInWithEmail(email: String, password: String) {
        return authDataSource.signInWithEmail(email, password)
    }

    override suspend fun signInWithGoogle(idToken: String) {
        return authDataSource.signInWithGoogle(idToken)
    }

    override suspend fun getCurrentUser(): FirebaseUser? {
        return authDataSource.getCurrentUser()
    }

    override suspend fun signOut() {
        return authDataSource.signOut()
    }
}