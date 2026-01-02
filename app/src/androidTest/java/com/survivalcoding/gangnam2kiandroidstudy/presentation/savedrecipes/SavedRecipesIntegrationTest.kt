package com.survivalcoding.gangnam2kiandroidstudy.presentation.savedrecipes

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.survivalcoding.gangnam2kiandroidstudy.data.datasource.BookmarkDao
import com.survivalcoding.gangnam2kiandroidstudy.data.datasource.BookmarkDatabase
import com.survivalcoding.gangnam2kiandroidstudy.data.repository.BookmarkRepositoryImpl
import com.survivalcoding.gangnam2kiandroidstudy.domain.repository.BookmarkRepository
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import kotlin.test.assertContains
import kotlin.test.assertTrue

@RunWith(AndroidJUnit4::class)
class SavedRecipesIntegrationTest {
    private lateinit var db: BookmarkDatabase
    private lateinit var dao: BookmarkDao
    private lateinit var repository: BookmarkRepository

    @Before
    fun setup() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        db = Room.inMemoryDatabaseBuilder(context, BookmarkDatabase::class.java).allowMainThreadQueries().build()
        dao = db.bookmarkDao()
        repository = BookmarkRepositoryImpl(dao)
    }

    @After
    fun tearDown() {
        db.close()
    }

    @Test
    fun 북마크가_정상적으로_동작하는지_테스트() = runTest {
        // 북마크 추가
        repository.toggleBookmark(recipeId = 10, isBookmarked = false)

        val bookmarked = repository.getBookmarkedRecipeIds().first()
        assertContains(bookmarked, 10)

        // 북마크 해제
        repository.toggleBookmark(recipeId = 10, isBookmarked = true)

        val updated = repository.getBookmarkedRecipeIds().first()
        assertTrue(!updated.contains(10))
    }
}