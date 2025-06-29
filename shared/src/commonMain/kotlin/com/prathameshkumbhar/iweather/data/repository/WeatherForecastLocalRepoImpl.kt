package com.prathameshkumbhar.iweather.data.repository

import com.prathameshkumbhar.iweather.database.dto.toWeatherForecast
import com.prathameshkumbhar.iweather.database.entity.IWeatherDB
import com.prathameshkumbhar.iweather.database.model.WeatherForecast
import com.prathameshkumbhar.iweather.domain.repository.WeatherForecastLocalRepository


class WeatherForecastLocalRepoImpl(
    private val db: IWeatherDB
) : WeatherForecastLocalRepository {

    private val queries = db.weatherForecastEntityQueries

    override suspend fun insertForecast(forecast: WeatherForecast) {
        queries.insertWeatherForecast(
            interval = forecast.currentWeather?.interval?.toLong(),
            is_day = forecast.currentWeather?.isDay?.toLong(),
            temperature = forecast.currentWeather?.temperature,
            time = forecast.currentWeather?.time,
            weathercode = forecast.currentWeather?.weathercode?.toLong(),
            winddirection = forecast.currentWeather?.winddirection?.toLong(),
            windspeed = forecast.currentWeather?.windspeed,
            interval_unit = forecast.currentWeatherUnits?.interval,
            is_day_unit = forecast.currentWeatherUnits?.isDay,
            temperature_unit = forecast.currentWeatherUnits?.temperature,
            time_unit = forecast.currentWeatherUnits?.time,
            weathercode_unit = forecast.currentWeatherUnits?.weathercode,
            winddirection_unit = forecast.currentWeatherUnits?.winddirection,
            windspeed_unit = forecast.currentWeatherUnits?.windspeed,
            elevation = forecast.elevation,
            generationtime_ms = forecast.generationtimeMs,
            latitude = forecast.latitude,
            longitude = forecast.longitude,
            timezone = forecast.timezone,
            timezone_abbreviation = forecast.timezoneAbbreviation,
            utc_offset_seconds = forecast.utcOffsetSeconds?.toLong()
        )
    }

    override suspend fun getLatestForecast(): WeatherForecast? {
        return queries.selectLatest().executeAsOneOrNull()?.toWeatherForecast()
    }
}