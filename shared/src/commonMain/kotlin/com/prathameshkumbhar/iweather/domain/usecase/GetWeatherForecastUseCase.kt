package com.prathameshkumbhar.iweather.domain.usecase

import com.prathameshkumbhar.iweather.connection.models.request.GetWeatherForecastRequest
import com.prathameshkumbhar.iweather.connection.models.response.GetWeatherForecastResponse
import com.prathameshkumbhar.iweather.domain.repository.WeatherForecastRepository
import com.prathameshkumbhar.iweather.utils.NetworkResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class GetWeatherForecastUseCase(
    private val repository: WeatherForecastRepository
) {
    operator fun invoke(getWeatherForecastRequest: GetWeatherForecastRequest): Flow<NetworkResult<GetWeatherForecastResponse>> =
        flow {
            emit(NetworkResult.Loading())
            val data = repository.getCurrentWeatherForecast(
                getWeatherForecastRequest.latitude,
                getWeatherForecastRequest.longitude,
                getWeatherForecastRequest.currentWeather
            )
            emit(NetworkResult.Success(data))
        }.catch {
            emit(NetworkResult.Error(it.message ?: "Unknown error"))
        }.flowOn(Dispatchers.IO)
}