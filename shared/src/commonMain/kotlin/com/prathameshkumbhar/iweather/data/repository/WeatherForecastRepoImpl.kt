package com.prathameshkumbhar.iweather.data.repository

import com.prathameshkumbhar.iweather.connection.ApiCommunicator
import com.prathameshkumbhar.iweather.connection.models.response.GetWeatherForecastResponse
import com.prathameshkumbhar.iweather.core.NetworkMonitor
import com.prathameshkumbhar.iweather.database.dto.toWeatherForecast
import com.prathameshkumbhar.iweather.database.dto.toWeatherForecastResponse
import com.prathameshkumbhar.iweather.domain.repository.WeatherForecastLocalRepository
import com.prathameshkumbhar.iweather.domain.repository.WeatherForecastRepository

class WeatherForecastRepoImpl(
    private val apiCommunicator: ApiCommunicator,
    private val weatherForecastLocalRepository: WeatherForecastLocalRepository,
    private val networkMonitor: NetworkMonitor
) : WeatherForecastRepository {

    override suspend fun getCurrentWeatherForecast(
        latitude: Double,
        longitude: Double,
        currentWeather: Boolean
    ): GetWeatherForecastResponse {
        return if (networkMonitor.isOnline()) {
            val response = apiCommunicator.getCurrentWeatherForecast(
                latitude = latitude,
                longitude = longitude,
                currentWeather = currentWeather
            )
            weatherForecastLocalRepository.insertForecast(response.toWeatherForecast())
            response
        } else {
            weatherForecastLocalRepository.getLatestForecast()
                ?.toWeatherForecastResponse()
                ?: throw Exception("No offline data available")
        }
    }
}
