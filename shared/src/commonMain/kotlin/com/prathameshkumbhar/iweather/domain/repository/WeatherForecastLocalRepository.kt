package com.prathameshkumbhar.iweather.domain.repository

import com.prathameshkumbhar.iweather.database.model.WeatherForecast

interface WeatherForecastLocalRepository {
    suspend fun insertForecast(forecast: WeatherForecast)
    suspend fun getLatestForecast(): WeatherForecast?
}