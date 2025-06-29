package com.prathameshkumbhar.iweather

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform