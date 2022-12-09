package com.travel.news.app.persistence.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tourists")
data class TouristEntity(
    @PrimaryKey
    val id: Int?,
    val name: String?,
    val email: String?,
    val location: String?,
    val createdDate: String?,
)
