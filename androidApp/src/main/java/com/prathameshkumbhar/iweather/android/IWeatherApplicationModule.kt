package com.prathameshkumbhar.iweather.android

import com.prathameshkumbhar.iweather.android.viewmodel.AndroidWeatherForecastViewModel
import com.prathameshkumbhar.iweather.core.NetworkMonitor
import com.prathameshkumbhar.iweather.utils.AndroidNetworkMonitor
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val androidViewModelModule = module {
    single<NetworkMonitor> {
        AndroidNetworkMonitor(androidContext())
    }

    viewModel {
        AndroidWeatherForecastViewModel(
            getWeatherUseCase = get(),
            networkMonitor = get()
        )
    }
}