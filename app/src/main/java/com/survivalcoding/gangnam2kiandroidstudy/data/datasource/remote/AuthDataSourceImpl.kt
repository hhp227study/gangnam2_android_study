package com.survivalcoding.gangnam2kiandroidstudy.data.datasource.remote

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import com.survivalcoding.gangnam2kiandroidstudy.data.datasource.AuthDataSource
import com.survivalcoding.gangnam2kiandroidstudy.domain.model.User
import kotlinx.coroutines.tasks.await

class AuthDataSourceImpl(private val firebaseAuth: FirebaseAuth) : AuthDataSource {
    override suspend fun signUpWithEmail(email: String, password: String) {
        firebaseAuth
            .createUserWithEmailAndPassword(email, password)
            .await()
    }

    override suspend fun signInWithEmail(email: String, password: String) {
        firebaseAuth
            .signInWithEmailAndPassword(email, password)
            .await()
    }

    override suspend fun signInWithGoogle(idToken: String) {
        val credential = GoogleAuthProvider.getCredential(idToken, null)
        firebaseAuth.signInWithCredential(credential).await()
    }

    override suspend fun getCurrentUser(): User? {
        return firebaseAuth.currentUser?.let {
            User(
                uid = it.uid,
                email = it.email,
                displayName = it.displayName
            )
        }
    }

    override suspend fun signOut() {
        firebaseAuth.signOut()
    }
}