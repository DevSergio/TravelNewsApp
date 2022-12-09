package com.travel.news.app.domain.model

data class Post(
    val id: Int? = null,
    val title: String? = null,
    val description: String? = null,
    val location: String? = null,
    val multimedia: List<Multimedia>?,
    val createdDate: String?,
    val user: User? = null,
    val commentCount: Int? = null
)
