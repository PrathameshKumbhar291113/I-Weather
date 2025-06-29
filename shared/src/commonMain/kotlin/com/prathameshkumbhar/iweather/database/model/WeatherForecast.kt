package com.prathameshkumbhar.iweather.database.model

data class WeatherForecast(
    val currentWeather: CurrentWeather?,
    val currentWeatherUnits: CurrentWeatherUnits?,
    val elevation: Double?,
    val generationtimeMs: Double?,
    val latitude: Double?,
    val longitude: Double?,
    val timezone: String?,
    val timezoneAbbreviation: String?,
    val utcOffsetSeconds: Int?
) {
    data class CurrentWeather(
        val interval: Int?,
        val isDay: Int?,
        val temperature: Double?,
        val time: String?,
        val weathercode: Int?,
        val winddirection: Int?,
        val windspeed: Double?
    )

    data class CurrentWeatherUnits(
        val interval: String?,
        val isDay: String?,
        val temperature: String?,
        val time: String?,
        val weathercode: String?,
        val winddirection: String?,
        val windspeed: String?
    )
}

