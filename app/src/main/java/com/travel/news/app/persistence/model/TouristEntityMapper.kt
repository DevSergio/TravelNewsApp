package com.travel.news.app.persistence.model

import com.travel.news.app.domain.model.Tourist
import com.travel.news.app.domain.util.DomainMapper

class TouristEntityMapper : DomainMapper<TouristEntity, Tourist> {

    override fun mapToDomainModel(model: TouristEntity): Tourist {
        return Tourist(
            id = model.id,
            name = model.name,
            email = model.email,
            location = model.location,
            dateCreated = model.createdDate
        )
    }

    override fun mapFromDomainModel(domainModel: Tourist): TouristEntity {
        return TouristEntity(
            id = domainModel.id,
            name = domainModel.name,
            email = domainModel.email,
            location = domainModel.location,
            createdDate = domainModel.dateCreated
        )
    }

    fun fromEntityList(initial: List<TouristEntity>): List<Tourist> {
        return initial.map { mapToDomainModel(it) }
    }

    fun toEntityList(initial: List<Tourist>): List<TouristEntity> {
        return initial.map { mapFromDomainModel(it) }
    }
}