package com.prathameshkumbhar.iweather.database

import com.prathameshkumbhar.iweather.database.entity.IWeatherDB
import org.koin.dsl.module

val databaseModule = module {
    single<IWeatherDB> {
        IWeatherDB(
            driver = DatabaseDriverFactory(get()).createDriver()
        )
    }
}