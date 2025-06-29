package com.prathameshkumbhar.iweather.domain.repository

import com.prathameshkumbhar.iweather.connection.models.response.GetWeatherForecastResponse

interface WeatherForecastRepository {
    suspend fun getCurrentWeatherForecast(
        latitude: Double,
        longitude: Double,
        currentWeather: Boolean
    ): GetWeatherForecastResponse
}