package com.prathameshkumbhar.iweather.data.di

import com.prathameshkumbhar.iweather.connection.ApiCommunicator
import com.prathameshkumbhar.iweather.core.NetworkMonitor
import com.prathameshkumbhar.iweather.data.repository.WeatherForecastLocalRepoImpl
import com.prathameshkumbhar.iweather.data.repository.WeatherForecastRepoImpl
import com.prathameshkumbhar.iweather.database.entity.IWeatherDB
import com.prathameshkumbhar.iweather.domain.repository.WeatherForecastLocalRepository
import com.prathameshkumbhar.iweather.domain.repository.WeatherForecastRepository
import org.koin.dsl.module


val weatherDataModule = module {
    single<WeatherForecastRepository> {
        WeatherForecastRepoImpl(
            get<ApiCommunicator>(),
            get<WeatherForecastLocalRepository>(),
            get<NetworkMonitor>()
        )
    }

    single<WeatherForecastLocalRepository> {
        WeatherForecastLocalRepoImpl(get<IWeatherDB>())
    }
}