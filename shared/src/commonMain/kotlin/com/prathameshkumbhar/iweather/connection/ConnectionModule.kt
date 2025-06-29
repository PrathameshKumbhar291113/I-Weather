package com.prathameshkumbhar.iweather.connection


import com.prathameshkumbhar.iweather.connection.ApiCommunicator
import com.prathameshkumbhar.iweather.connection.ApiCommunicatorImpl
import io.ktor.client.HttpClient
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logging
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import org.koin.dsl.module

val networkModule = module {
    single {
        HttpClient {
            install(ContentNegotiation) {
                json(Json {
                    ignoreUnknownKeys = true
                    isLenient = true
                })
            }
            install(Logging) {
                level = LogLevel.BODY
            }
        }
    }

    single<ApiCommunicator> { ApiCommunicatorImpl(get()) }
}