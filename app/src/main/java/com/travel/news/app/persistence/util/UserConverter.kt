package com.travel.news.app.persistence.util

import androidx.room.TypeConverter
import com.google.gson.reflect.TypeToken
import com.travel.news.app.domain.model.User

object UserConverter {
    @TypeConverter
    fun fromUser(user: User): String {
        return MultimediaConverter.gson.toJson(user)
    }

    @TypeConverter
    fun toUser(data: String): User {
        val listType = object : TypeToken<User>() {
        }.type
        return MultimediaConverter.gson.fromJson(data, listType)
    }
}