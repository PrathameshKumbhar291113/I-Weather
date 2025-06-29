package com.prathameshkumbhar.iweather.domain.di

import com.prathameshkumbhar.iweather.domain.repository.WeatherForecastRepository
import com.prathameshkumbhar.iweather.domain.usecase.GetWeatherForecastUseCase
import org.koin.dsl.module

val weatherDomainModule = module {
    single { GetWeatherForecastUseCase(get<WeatherForecastRepository>()) }
}