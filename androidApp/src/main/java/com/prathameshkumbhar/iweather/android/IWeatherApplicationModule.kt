package com.prathameshkumbhar.iweather.android

import com.prathameshkumbhar.iweather.android.viewmodel.AndroidWeatherForecastViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val androidViewModelModule = module {
    viewModel {
        AndroidWeatherForecastViewModel(get())
    }
}