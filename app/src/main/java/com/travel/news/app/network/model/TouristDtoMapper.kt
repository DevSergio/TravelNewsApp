package com.travel.news.app.network.model

import com.travel.news.app.domain.model.Tourist
import com.travel.news.app.domain.util.DomainMapper

class TouristDtoMapper : DomainMapper<TouristDto, Tourist> {

    override fun mapToDomainModel(model: TouristDto): Tourist {
        return Tourist(
            id = model.id,
            name = model.name,
            email = model.email,
            location = model.location,
            dateCreated = model.createdDate
        )
    }

    override fun mapFromDomainModel(domainModel: Tourist): TouristDto {
        return TouristDto(
            id = domainModel.id,
            name = domainModel.name,
            email = domainModel.email,
            location = domainModel.location,
            createdDate = domainModel.dateCreated,
        )
    }

    fun toDomainList(initial: List<TouristDto>): List<Tourist> {
        return initial.map { mapToDomainModel(it) }
    }

    fun toDomainDetails(initial: TouristDto): Tourist {
        return Tourist(
            id = initial.id,
            name = initial.name,
            email = initial.email,
            location = initial.location,
            dateCreated = initial.createdDate
        )
    }

    fun fromDomainList(initial: List<Tourist>): List<TouristDto> {
        return initial.map { mapFromDomainModel(it) }
    }
}