package com.travel.news.app.di

import com.travel.news.app.network.NewsService
import com.travel.news.app.network.model.PostDtoMapper
import com.travel.news.app.network.model.TouristDtoMapper
import com.travel.news.app.persistence.PostDao
import com.travel.news.app.persistence.model.PostEntityMapper
import com.travel.news.app.persistence.model.TouristEntityMapper
import com.travel.news.app.repository.NewsFeedRepository
import com.travel.news.app.repository.TouristDetailsRepository
import com.travel.news.app.repository.TouristListRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
object RepositoryModule {
    @Provides
    @ViewModelScoped
    fun provideNewsRepository(
        disneyService: NewsService,
        posterDao: PostDao,
        postDtoMapper: PostDtoMapper,
        postEntityMapper: PostEntityMapper
    ): NewsFeedRepository {
        return NewsFeedRepository(disneyService, posterDao, postDtoMapper, postEntityMapper)
    }

    @Provides
    @ViewModelScoped
    fun provideTouristsRepository(
        disneyService: NewsService,
        posterDao: PostDao,
        touristDtoMapper: TouristDtoMapper,
        touristEntityMapper: TouristEntityMapper
    ): TouristListRepository {
        return TouristListRepository(
            disneyService,
            posterDao,
            touristDtoMapper,
            touristEntityMapper
        )
    }

    @Provides
    @ViewModelScoped
    fun provideTouristDetailsRepository(
        disneyService: NewsService,
        touristDtoMapper: TouristDtoMapper
    ): TouristDetailsRepository {
        return TouristDetailsRepository(
            disneyService,
            touristDtoMapper
        )
    }
}