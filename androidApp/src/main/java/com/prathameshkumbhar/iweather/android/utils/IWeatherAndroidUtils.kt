package com.prathameshkumbhar.iweather.android.utils

import java.text.SimpleDateFormat
import java.util.Locale
import java.util.TimeZone

fun formatWeatherTime(timeString: String, timeZoneFromApi: String): String {
    return try {
        val inputFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm", Locale.getDefault()).apply {
            timeZone = TimeZone.getTimeZone(timeZoneFromApi)
        }

        val outputFormat = SimpleDateFormat("MMM dd, hh:mm a", Locale.getDefault()).apply {
            timeZone = TimeZone.getTimeZone("Asia/Kolkata")
        }

        val date = inputFormat.parse(timeString)
        if (date != null) outputFormat.format(date) else timeString
    } catch (e: Exception) {
        timeString
    }
}

