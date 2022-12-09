package com.travel.news.app.network.model

import com.google.gson.annotations.SerializedName

data class TouristDto(
    @SerializedName("id")
    var id: Int?,
    @SerializedName("tourist_name")
    var name: String?,
    @SerializedName("tourist_email")
    var email: String?,
    @SerializedName("tourist_location")
    var location: String?,
    @SerializedName("createdat")
    var createdDate: String?,
)