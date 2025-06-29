package com.prathameshkumbhar.iweather.android.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.prathameshkumbhar.iweather.domain.usecase.GetWeatherForecastUseCase
import kotlinx.coroutines.flow.StateFlow
import com.prathameshkumbhar.iweather.utils.NetworkResult
import com.prathameshkumbhar.iweather.connection.models.response.GetWeatherForecastResponse
import com.prathameshkumbhar.iweather.presentation.viewmodel.WeatherForecastViewModel
import kotlinx.coroutines.launch

class AndroidWeatherForecastViewModel(
    getWeatherUseCase: GetWeatherForecastUseCase
) : ViewModel() {

    private val sharedViewModel = WeatherForecastViewModel(getWeatherUseCase)

    val state: StateFlow<NetworkResult<GetWeatherForecastResponse>> = sharedViewModel.state

    fun loadWeather(latitude: Double, longitude: Double, currentWeather: Boolean) {
        viewModelScope.launch {
            sharedViewModel.loadWeather(latitude, longitude, currentWeather)
        }
    }
}
