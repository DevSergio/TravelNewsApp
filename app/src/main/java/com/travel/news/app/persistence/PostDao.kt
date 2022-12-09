package com.travel.news.app.persistence

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.travel.news.app.persistence.model.PostEntity
import com.travel.news.app.persistence.model.TouristEntity

@Dao
interface PostDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPosterList(posters: List<PostEntity>)

    @Query("SELECT * FROM POSTS WHERE id = :id_")
    suspend fun getNewsFeed(id_: Long): List<PostEntity>?

    @Query("SELECT * FROM POSTS")
    suspend fun getNewsFeed(): List<PostEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTouristList(posters: List<TouristEntity>)

    @Query("SELECT * FROM TOURISTS WHERE id = :id_")
    suspend fun getTourists(id_: Long): List<TouristEntity>?

    @Query("SELECT * FROM TOURISTS")
    suspend fun getTourists(): List<TouristEntity>
}