package com.survivalcoding.gangnam2kiandroidstudy.data.datasource.remote

import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.FirebaseFirestore
import com.survivalcoding.gangnam2kiandroidstudy.data.datasource.BookmarkDataSource
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.tasks.await

class BookmarkDataSourceImpl(
    private val firestore: FirebaseFirestore
) : BookmarkDataSource {
    override fun observeBookmarks(uid: String): Flow<List<Int>> = callbackFlow {
        val listener = firestore
            .collection("users")
            .document(uid)
            .collection("bookmarks")
            .addSnapshotListener { snapshot, error ->
                if (error != null) {
                    close(error)
                } else {
                    val ids = snapshot?.documents
                        ?.mapNotNull { it.getLong("recipeId")?.toInt() }
                        ?: emptyList()

                    trySend(ids)
                }
            }

        awaitClose { listener.remove() }
    }

    override suspend fun addBookmark(uid: String, recipeId: Int) {
        firestore
            .collection("users")
            .document(uid)
            .collection("bookmarks")
            .document(recipeId.toString())
            .set(
                mapOf(
                    "recipeId" to recipeId,
                    "createdAt" to FieldValue.serverTimestamp()
                )
            )
            .await()
    }

    override suspend fun removeBookmark(uid: String, recipeId: Int) {
        firestore
            .collection("users")
            .document(uid)
            .collection("bookmarks")
            .document(recipeId.toString())
            .delete()
            .await()
    }
}