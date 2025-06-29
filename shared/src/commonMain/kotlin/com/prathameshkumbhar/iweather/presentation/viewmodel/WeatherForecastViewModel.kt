package com.prathameshkumbhar.iweather.presentation.viewmodel

import com.prathameshkumbhar.iweather.connection.models.request.GetWeatherForecastRequest
import com.prathameshkumbhar.iweather.connection.models.response.GetWeatherForecastResponse
import com.prathameshkumbhar.iweather.domain.usecase.GetWeatherForecastUseCase
import com.prathameshkumbhar.iweather.presentation.BaseViewModel
import com.prathameshkumbhar.iweather.utils.NetworkResult
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class WeatherForecastViewModel(
    private val getWeatherUseCase: GetWeatherForecastUseCase
) : BaseViewModel() {

    private val _state =
        MutableStateFlow<NetworkResult<GetWeatherForecastResponse>>(NetworkResult.Loading())
    val state: StateFlow<NetworkResult<GetWeatherForecastResponse>> = _state

    fun loadWeather(latitude: Double, longitude: Double, currentWeather: Boolean) {
        viewModelScope.launch {
            getWeatherUseCase(
                GetWeatherForecastRequest(latitude, longitude, currentWeather)
            ).collect {
                _state.value = it
            }
        }
    }
}