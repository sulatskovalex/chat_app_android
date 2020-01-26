package com.github.sulatskovalex.domain

sealed class ResultValue<T> {
    companion object {
        fun <T> success(value: T) = Success(value)
        fun <T> error(value: Throwable) = Error<T>(value)
    }

    data class Success<T> constructor(val value: T) : ResultValue<T>()
    data class Error<T> constructor(val error: Throwable) : ResultValue<T>()
}

