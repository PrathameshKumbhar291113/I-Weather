package com.prathameshkumbhar.iweather.database.dto

import com.prathameshkumbhar.iweather.connection.models.response.GetWeatherForecastResponse
import com.prathameshkumbhar.iweather.database.entity.WeatherForecastEntity
import com.prathameshkumbhar.iweather.database.model.WeatherForecast

fun WeatherForecastEntity.toWeatherForecast(): WeatherForecast {
    return WeatherForecast(
        currentWeather = WeatherForecast.CurrentWeather(
            interval = interval?.toInt(),
            isDay = is_day?.toInt(),
            temperature = temperature,
            time = time,
            weathercode = weathercode?.toInt(),
            winddirection = winddirection?.toInt(),
            windspeed = windspeed
        ),
        currentWeatherUnits = WeatherForecast.CurrentWeatherUnits(
            interval = interval_unit,
            isDay = is_day_unit,
            temperature = temperature_unit,
            time = time_unit,
            weathercode = weathercode_unit,
            winddirection = winddirection_unit,
            windspeed = windspeed_unit
        ),
        elevation = elevation,
        generationtimeMs = generationtime_ms,
        latitude = latitude,
        longitude = longitude,
        timezone = timezone,
        timezoneAbbreviation = timezone_abbreviation,
        utcOffsetSeconds = utc_offset_seconds?.toInt()
    )
}


fun GetWeatherForecastResponse.toWeatherForecast(): WeatherForecast {
    return WeatherForecast(
        currentWeather = this.currentWeather?.let {
            WeatherForecast.CurrentWeather(
                interval = it.interval,
                isDay = it.isDay,
                temperature = it.temperature,
                time = it.time,
                weathercode = it.weathercode,
                winddirection = it.winddirection,
                windspeed = it.windspeed
            )
        },
        currentWeatherUnits = this.currentWeatherUnits?.let {
            WeatherForecast.CurrentWeatherUnits(
                interval = it.interval,
                isDay = it.isDay,
                temperature = it.temperature,
                time = it.time,
                weathercode = it.weathercode,
                winddirection = it.winddirection,
                windspeed = it.windspeed
            )
        },
        elevation = this.elevation,
        generationtimeMs = this.generationtimeMs,
        latitude = this.latitude,
        longitude = this.longitude,
        timezone = this.timezone,
        timezoneAbbreviation = this.timezoneAbbreviation,
        utcOffsetSeconds = this.utcOffsetSeconds
    )
}

fun WeatherForecast.toWeatherForecastResponse(): GetWeatherForecastResponse {
    return GetWeatherForecastResponse(
        currentWeather = this.currentWeather?.let {
            GetWeatherForecastResponse.CurrentWeather(
                interval = it.interval,
                isDay = it.isDay,
                temperature = it.temperature,
                time = it.time,
                weathercode = it.weathercode,
                winddirection = it.winddirection,
                windspeed = it.windspeed
            )
        },
        currentWeatherUnits = this.currentWeatherUnits?.let {
            GetWeatherForecastResponse.CurrentWeatherUnits(
                interval = it.interval,
                isDay = it.isDay,
                temperature = it.temperature,
                time = it.time,
                weathercode = it.weathercode,
                winddirection = it.winddirection,
                windspeed = it.windspeed
            )
        },
        elevation = this.elevation,
        generationtimeMs = this.generationtimeMs,
        latitude = this.latitude,
        longitude = this.longitude,
        timezone = this.timezone,
        timezoneAbbreviation = this.timezoneAbbreviation,
        utcOffsetSeconds = this.utcOffsetSeconds
    )
}
