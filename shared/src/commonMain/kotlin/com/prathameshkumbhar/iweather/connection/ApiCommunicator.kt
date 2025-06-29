package com.prathameshkumbhar.iweather.connection

import com.prathameshkumbhar.iweather.connection.models.response.GetWeatherForecastResponse

interface ApiCommunicator {
    suspend fun getCurrentWeatherForecast(
        latitude: Double,
        longitude: Double,
        currentWeather: Boolean
    ): GetWeatherForecastResponse
}