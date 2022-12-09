package com.travel.news.app.repository

import androidx.annotation.WorkerThread
import com.travel.news.app.domain.data.DataState
import com.travel.news.app.domain.model.Post
import com.travel.news.app.network.NewsService
import com.travel.news.app.network.model.PostDtoMapper
import com.travel.news.app.persistence.PostDao
import com.travel.news.app.persistence.model.PostEntityMapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class NewsFeedRepository @Inject constructor(
    private val newsService: NewsService,
    private val postDao: PostDao,
    private val postDtoMapper: PostDtoMapper,
    private val postEntityMapper: PostEntityMapper
) {

    @WorkerThread
    fun loadNewsFeed(
        page: Int
    ): Flow<DataState<List<Post>>> = flow {
        try {
            emit(DataState.loading())

            val posts = getNewsFromCache()

            if (posts?.isNotEmpty() == true && page == 1) {
                emit(DataState.success(posts))
            } else {
                val networkPosts = getNewsFromNetwork(page)
                postDao.insertPosterList(postEntityMapper.toEntityList(networkPosts))
                emit(DataState.success(networkPosts))
            }
        } catch (e: Exception) {
            emit(DataState.error(e.message ?: "Unknown Error"))
        }
    }

    private suspend fun getNewsFromCache(): List<Post>? {
        return postDao.getNewsFeed().let { postEntity ->
            postEntityMapper.fromEntityList(postEntity)
        }
    }

    private suspend fun getNewsFromNetwork(page: Int): List<Post> {
        return postDtoMapper.toDomainList(newsService.getNewsFeed(page).posts)
    }
}