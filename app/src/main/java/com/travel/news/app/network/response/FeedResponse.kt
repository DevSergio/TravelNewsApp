package com.travel.news.app.network.response

import com.google.gson.annotations.SerializedName
import com.travel.news.app.network.model.PostDto

data class FeedResponse(
    @SerializedName("page")
    var page: Int,
    @SerializedName("totalrecord")
    var count: Int,
    @SerializedName("total_pages")
    var totalPages: Int,
    @SerializedName("data")
    var posts: List<PostDto>,
)