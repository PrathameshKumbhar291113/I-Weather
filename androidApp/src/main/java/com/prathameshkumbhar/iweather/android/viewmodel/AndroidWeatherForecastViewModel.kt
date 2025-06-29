package com.prathameshkumbhar.iweather.android.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.prathameshkumbhar.iweather.connection.models.response.GetWeatherForecastResponse
import com.prathameshkumbhar.iweather.core.NetworkMonitor
import com.prathameshkumbhar.iweather.domain.usecase.GetWeatherForecastUseCase
import com.prathameshkumbhar.iweather.presentation.viewmodel.WeatherForecastViewModel
import com.prathameshkumbhar.iweather.utils.NetworkResult
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class AndroidWeatherForecastViewModel(
    private val getWeatherUseCase: GetWeatherForecastUseCase,
    private val networkMonitor: NetworkMonitor
) : ViewModel() {


    private val sharedViewModel = WeatherForecastViewModel(getWeatherUseCase)
    val state: StateFlow<NetworkResult<GetWeatherForecastResponse>> = sharedViewModel.state

    private val _isNetworkAvailable = MutableStateFlow(networkMonitor.isOnline())
    val isNetworkAvailable: StateFlow<Boolean> get() = _isNetworkAvailable

    fun loadWeather(latitude: Double, longitude: Double, currentWeather: Boolean) {
        viewModelScope.launch {
            _isNetworkAvailable.value = networkMonitor.isOnline()
            sharedViewModel.loadWeather(latitude, longitude, currentWeather)
        }
    }
}
