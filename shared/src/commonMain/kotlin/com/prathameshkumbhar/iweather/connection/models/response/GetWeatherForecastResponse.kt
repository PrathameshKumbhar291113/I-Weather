package com.prathameshkumbhar.iweather.connection.models.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GetWeatherForecastResponse(
    @SerialName("current_weather")
    val currentWeather: CurrentWeather? = null,

    @SerialName("current_weather_units")
    val currentWeatherUnits: CurrentWeatherUnits? = null,

    @SerialName("elevation")
    val elevation: Double? = null,

    @SerialName("generationtime_ms")
    val generationtimeMs: Double? = null,

    @SerialName("latitude")
    val latitude: Double? = null,

    @SerialName("longitude")
    val longitude: Double? = null,

    @SerialName("timezone")
    val timezone: String? = null,

    @SerialName("timezone_abbreviation")
    val timezoneAbbreviation: String? = null,

    @SerialName("utc_offset_seconds")
    val utcOffsetSeconds: Int? = null
) {
    @Serializable
    data class CurrentWeather(
        @SerialName("interval")
        val interval: Int? = null,

        @SerialName("is_day")
        val isDay: Int? = null,

        @SerialName("temperature")
        val temperature: Double? = null,

        @SerialName("time")
        val time: String? = null,

        @SerialName("weathercode")
        val weathercode: Int? = null,

        @SerialName("winddirection")
        val winddirection: Int? = null,

        @SerialName("windspeed")
        val windspeed: Double? = null
    )

    @Serializable
    data class CurrentWeatherUnits(
        @SerialName("interval")
        val interval: String? = null,

        @SerialName("is_day")
        val isDay: String? = null,

        @SerialName("temperature")
        val temperature: String? = null,

        @SerialName("time")
        val time: String? = null,

        @SerialName("weathercode")
        val weathercode: String? = null,

        @SerialName("winddirection")
        val winddirection: String? = null,

        @SerialName("windspeed")
        val windspeed: String? = null
    )
}
