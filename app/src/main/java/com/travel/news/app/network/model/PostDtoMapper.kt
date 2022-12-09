package com.travel.news.app.network.model

import com.travel.news.app.domain.model.Post
import com.travel.news.app.domain.util.DomainMapper

class PostDtoMapper : DomainMapper<PostDto, Post> {

    override fun mapToDomainModel(model: PostDto): Post {
        return Post(
            id = model.id,
            title = model.title,
            description = model.description,
            location = model.location,
            multimedia = model.multimedia,
            createdDate = model.createdDate,
            user = model.user,
            commentCount = model.commentsCount
        )
    }

    override fun mapFromDomainModel(domainModel: Post): PostDto {
        return PostDto(
            id = domainModel.id,
            title = domainModel.title,
            description = domainModel.description,
            location = domainModel.location,
            multimedia = domainModel.multimedia,
            createdDate = domainModel.createdDate,
            user = domainModel.user,
            commentsCount = domainModel.commentCount
        )
    }

    fun toDomainList(initial: List<PostDto>): List<Post> {
        return initial.map { mapToDomainModel(it) }
    }

    fun fromDomainList(initial: List<Post>): List<PostDto> {
        return initial.map { mapFromDomainModel(it) }
    }
}