package com.prathameshkumbhar.iweather.data.repository

import com.prathameshkumbhar.iweather.connection.ApiCommunicator
import com.prathameshkumbhar.iweather.connection.models.response.GetWeatherForecastResponse
import com.prathameshkumbhar.iweather.domain.repository.WeatherForecastRepository

class WeatherForecastRepoImpl(
    private val api: ApiCommunicator
) : WeatherForecastRepository {
    override suspend fun getCurrentWeatherForecast(
        latitude: Double,
        longitude: Double,
        currentWeather: Boolean
    ): GetWeatherForecastResponse {
        return api.getCurrentWeatherForecast(latitude, longitude, currentWeather)
    }
}