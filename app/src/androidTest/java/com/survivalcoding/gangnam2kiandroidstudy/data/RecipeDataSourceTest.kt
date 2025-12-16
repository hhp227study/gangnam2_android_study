package com.survivalcoding.gangnam2kiandroidstudy.data

import androidx.test.ext.junit.runners.AndroidJUnit4
import com.survivalcoding.gangnam2kiandroidstudy.data.datasource.local.RecipeDataSourceImpl
import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class RecipeDataSourceTest {
    private val fakeJson = """
{
  "recipes":[
    {
      "category":"Indian",
      "id":1,
      "name":"Traditional spare ribs baked",
      "image":"https://cdn.pixabay.com/photo/2017/11/10/15/04/steak-2936531_1280.jpg",
      "chef":"Chef John",
      "time":"20 min",
      "rating":4.0
    },
    {
      "id":2,
      "category":"Asian",
      "name":"Spice roasted chicken with flavored rice",
      "image":"https://cdn.pixabay.com/photo/2018/12/04/16/49/tandoori-3856045_1280.jpg",
      "chef":"Mark Kelvin",
      "time":"20 min",
      "rating":4.0
    },
    {
      "id":3,
      "category":"Chinese",
      "name":"Spicy fried rice mix chicken bali",
      "image":"https://cdn.pixabay.com/photo/2019/09/07/19/02/spanish-paella-4459519_1280.jpg",
      "chef":"Spicy Nelly",
      "time":"20 min",
      "rating":4.0
    },
    {
      "category":"Japanese",
      "id":4,
      "name":"Ttekbokki",
      "image":"https://cdn.pixabay.com/photo/2017/07/27/16/48/toppokki-2545943_1280.jpg",
      "chef":"Kim Dahee",
      "time":"30 min",
      "rating":5.0
    }
  ]
}
    """.trimIndent()

    private val mockAssetManager = MockAssetManager(fakeJson)

    private val dataSource = RecipeDataSourceImpl.getInstance(mockAssetManager)

    @Test
    fun `테스트`() {
        val albumList = dataSource.getRecipes()
        val expected = 4

        assertEquals(expected, albumList.size)
    }
}