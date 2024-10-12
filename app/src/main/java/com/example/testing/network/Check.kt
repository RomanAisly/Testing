package com.example.testing.network

sealed class CheckConnection<T>(
    val data: T? = null,
    val message: String? = null
) {
    class Success<T>(data: T?) : CheckConnection<T>(data)
    class Error<T>(data: T? = null, message: String?) : CheckConnection<T>(data, message)
}