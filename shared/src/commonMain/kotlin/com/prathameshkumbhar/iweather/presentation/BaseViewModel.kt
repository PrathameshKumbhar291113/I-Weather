package com.prathameshkumbhar.iweather.presentation

import kotlinx.coroutines.CoroutineScope

expect open class BaseViewModel() {
    val viewModelScope: CoroutineScope
}