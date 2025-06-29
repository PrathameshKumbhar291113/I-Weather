package com.prathameshkumbhar.iweather.connection.models.request

data class GetWeatherForecastRequest(
    val latitude: Double,
    val longitude: Double,
    val currentWeather: Boolean
)