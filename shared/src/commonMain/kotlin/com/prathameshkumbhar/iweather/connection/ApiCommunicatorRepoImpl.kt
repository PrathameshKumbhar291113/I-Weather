package com.prathameshkumbhar.iweather.connection

import com.prathameshkumbhar.iweather.connection.models.response.GetWeatherForecastResponse
import com.prathameshkumbhar.iweather.utils.IWeatherNetworkConstants.BASE_URL
import com.prathameshkumbhar.iweather.utils.IWeatherNetworkConstants.GET_WEATHER_FORECAST
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import io.ktor.client.request.url
import io.ktor.http.appendPathSegments
import io.ktor.http.takeFrom

class ApiCommunicatorImpl(private val client: HttpClient) : ApiCommunicator {

    override suspend fun getCurrentWeatherForecast(
        latitude: Double,
        longitude: Double,
        currentWeather: Boolean
    ): GetWeatherForecastResponse {
        return client.get {
            url {
                takeFrom(BASE_URL)
                appendPathSegments(GET_WEATHER_FORECAST)
            }
            parameter("latitude", latitude)
            parameter("longitude", longitude)
            parameter("current_weather", currentWeather)
        }.body()
    }
}