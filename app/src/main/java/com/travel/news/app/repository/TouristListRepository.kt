package com.travel.news.app.repository

import androidx.annotation.WorkerThread
import com.travel.news.app.domain.data.DataState
import com.travel.news.app.domain.model.Tourist
import com.travel.news.app.network.NewsService
import com.travel.news.app.network.model.TouristDtoMapper
import com.travel.news.app.persistence.PostDao
import com.travel.news.app.persistence.model.TouristEntityMapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class TouristListRepository @Inject constructor(
    private val newsService: NewsService,
    private val postDao: PostDao,
    private val touristDtoMapper: TouristDtoMapper,
    private val touristEntityMapper: TouristEntityMapper
) {

    @WorkerThread
    fun loadTourists(
        page: Int
    ): Flow<DataState<List<Tourist>>> = flow {
        try {
            emit(DataState.loading())

            val tourists = getTouristFromCache()

            if (tourists?.isNotEmpty() == true && page == 1) {
                emit(DataState.success(tourists))
            } else {
                val networkTourists = getTouristsFromNetwork(page)
                postDao.insertTouristList(touristEntityMapper.toEntityList(networkTourists))
                emit(DataState.success(networkTourists))
            }

        } catch (e: Exception) {
            emit(DataState.error(e.message ?: "Unknown Error"))
        }
    }

    private suspend fun getTouristFromCache(): List<Tourist>? {
        return postDao.getTourists().let { postEntity ->
            touristEntityMapper.fromEntityList(postEntity)
        }
    }

    private suspend fun getTouristsFromNetwork(page: Int): List<Tourist> {
        return touristDtoMapper.toDomainList(newsService.getTourist(page).posts)
    }
}