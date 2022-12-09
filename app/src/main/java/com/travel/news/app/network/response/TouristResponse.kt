package com.travel.news.app.network.response

import com.google.gson.annotations.SerializedName
import com.travel.news.app.network.model.TouristDto

data class TouristResponse(
    @SerializedName("totalrecord")
    var count: Int,
    @SerializedName("data")
    var posts: List<TouristDto>,
)