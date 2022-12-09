package com.travel.news.app.persistence.util

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.travel.news.app.domain.model.Multimedia
import com.travel.news.app.domain.model.User


object MultimediaConverter {
    var gson = Gson()

    @TypeConverter
    fun fromMultimedia(multimedia: List<Multimedia>): String {
        return gson.toJson(multimedia)
    }

    @TypeConverter
    fun toMultimedia(data: String): List<Multimedia> {
        val listType = object : TypeToken<List<Multimedia>>() {
        }.type
        return gson.fromJson(data, listType)
    }
}