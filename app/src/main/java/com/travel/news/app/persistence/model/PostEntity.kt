package com.travel.news.app.persistence.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.travel.news.app.domain.model.Multimedia
import com.travel.news.app.domain.model.User

@Entity(tableName = "posts")
data class PostEntity(
    @PrimaryKey val id: Int?,
    val title: String?,
    val description: String?,
    val location: String?,
    val multimedia: List<Multimedia>?,
    val createdDate: String?,
    val user: User?,
    val commentsCount: Int?
)
