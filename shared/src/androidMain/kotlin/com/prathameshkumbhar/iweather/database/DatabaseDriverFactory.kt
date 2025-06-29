package com.prathameshkumbhar.iweather.database

import android.content.Context
import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.android.AndroidSqliteDriver
import com.prathameshkumbhar.iweather.database.entity.IWeatherDB

class DatabaseDriverFactory(private val context: Context) {
    fun createDriver(): SqlDriver {
        return AndroidSqliteDriver(IWeatherDB.Schema, context, "iweather.db")
    }
}