package com.travel.news.app.network

import com.travel.news.app.network.model.TouristDto
import com.travel.news.app.network.response.FeedResponse
import com.travel.news.app.network.response.TouristResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface NewsService {

    @GET("Feed/GetNewsFeed")
    suspend fun getNewsFeed(
        @Query("page") page: Int
    ): FeedResponse

    @GET("Tourist")
    suspend fun getTourist(
        @Query("page") page: Int
    ): TouristResponse

    @GET("Tourist/{id}")
    suspend fun getTouristDetails(
        @Path("id") id: Int
    ): TouristDto

}