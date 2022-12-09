package com.travel.news.app.network.model

import com.google.gson.annotations.SerializedName
import com.travel.news.app.domain.model.Multimedia
import com.travel.news.app.domain.model.User

data class PostDto(
    @SerializedName("id")
    var id: Int?,
    @SerializedName("title")
    var title: String?,
    @SerializedName("description")
    var description: String?,
    @SerializedName("location")
    var location: String?,
    @SerializedName("multiMedia")
    var multimedia: List<Multimedia>? = null,
    @SerializedName("createdat")
    var createdDate: String?,
    @SerializedName("user")
    var user: User?,
    @SerializedName("commentCount")
    var commentsCount: Int?,
)