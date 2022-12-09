package com.travel.news.app.persistence

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.travel.news.app.persistence.model.PostEntity
import com.travel.news.app.persistence.model.TouristEntity
import com.travel.news.app.persistence.util.MultimediaConverter
import com.travel.news.app.persistence.util.UserConverter

@Database(entities = [PostEntity::class, TouristEntity::class], version = 1, exportSchema = true)
@TypeConverters(MultimediaConverter::class, UserConverter::class)
abstract class AppDatabase : RoomDatabase() {

    abstract fun postDao(): PostDao

    companion object {
        const val DATABASE_NAME: String = "news_db"
    }
}