package com.prathameshkumbhar.iweather.data.di

import com.prathameshkumbhar.iweather.connection.ApiCommunicator
import com.prathameshkumbhar.iweather.data.repository.WeatherForecastRepoImpl
import com.prathameshkumbhar.iweather.domain.repository.WeatherForecastRepository
import org.koin.dsl.module


val weatherDataModule = module {
    single<WeatherForecastRepository> { WeatherForecastRepoImpl(get<ApiCommunicator>()) }
}