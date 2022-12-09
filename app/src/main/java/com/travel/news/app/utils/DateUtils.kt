package com.travel.news.app.utils

import java.text.SimpleDateFormat
import java.util.*

object DateUtils {

    const val INPUT_FORMAT = "yyyy-mm-dd'T'HH:mm:ss.SSS"
    const val OUTPUT_FORMAT = "MMMM dd, yyyy"

    fun String.getDateInFormat(inputFormat: String, outputFormat: String): String =
        SimpleDateFormat(inputFormat, Locale.getDefault()).parse(this)
            ?.let { SimpleDateFormat(outputFormat, Locale.getDefault()).format(it) } ?: ""
}