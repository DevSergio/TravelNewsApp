package com.travel.news.app.di

import androidx.room.Room
import com.travel.news.app.TravelNewsApp
import com.travel.news.app.persistence.AppDatabase
import com.travel.news.app.persistence.PostDao
import com.travel.news.app.persistence.model.PostEntityMapper
import com.travel.news.app.persistence.model.TouristEntityMapper
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object CacheModule {

    @Singleton
    @Provides
    fun provideDb(app: TravelNewsApp): AppDatabase {
        return Room
            .databaseBuilder(app, AppDatabase::class.java, AppDatabase.DATABASE_NAME)
            .fallbackToDestructiveMigration()
            .build()
    }

    @Singleton
    @Provides
    fun provideRecipeDao(db: AppDatabase): PostDao {
        return db.postDao()
    }

    @Singleton
    @Provides
    fun provideCachePostMapper(): PostEntityMapper {
        return PostEntityMapper()
    }

    @Singleton
    @Provides
    fun provideCacheTouristMapper(): TouristEntityMapper {
        return TouristEntityMapper()
    }

}