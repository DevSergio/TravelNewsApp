package com.travel.news.app.repository

import androidx.annotation.WorkerThread
import com.travel.news.app.domain.data.DataState
import com.travel.news.app.domain.model.Tourist
import com.travel.news.app.network.NewsService
import com.travel.news.app.network.model.TouristDtoMapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class TouristDetailsRepository @Inject constructor(
    private val newsService: NewsService,
    private val touristDtoMapper: TouristDtoMapper) {

    @WorkerThread
    fun loadTouristDetails(id: Int): Flow<DataState<Tourist>> = flow {
        try {
            emit(DataState.loading())
            val tourist = getTouristDetails(id)
            emit(DataState.success(tourist))
        } catch (e: Exception) {
            emit(DataState.error(e.message ?: "Unknown Error"))
        }
    }

    private suspend fun getTouristDetails(id: Int): Tourist {
        return touristDtoMapper.toDomainDetails(newsService.getTouristDetails(id))
    }
}