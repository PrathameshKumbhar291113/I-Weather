package com.prathameshkumbhar.iweather.android

import android.app.Application
import com.prathameshkumbhar.iweather.connection.networkModule
import com.prathameshkumbhar.iweather.data.di.weatherDataModule
import com.prathameshkumbhar.iweather.domain.di.weatherDomainModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.GlobalContext.startKoin

class IWeatherApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@IWeatherApplication)
            modules(
                networkModule,
                weatherDataModule,
                weatherDomainModule,
                androidViewModelModule
            )
        }
    }
}