package com.travel.news.app.persistence.model

import com.travel.news.app.domain.model.Post
import com.travel.news.app.domain.util.DomainMapper

class PostEntityMapper : DomainMapper<PostEntity, Post> {

    override fun mapToDomainModel(model: PostEntity): Post {
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

    override fun mapFromDomainModel(domainModel: Post): PostEntity {
        return PostEntity(
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

    fun fromEntityList(initial: List<PostEntity>): List<Post>{
        return initial.map { mapToDomainModel(it) }
    }

    fun toEntityList(initial: List<Post>): List<PostEntity>{
        return initial.map { mapFromDomainModel(it) }
    }

}