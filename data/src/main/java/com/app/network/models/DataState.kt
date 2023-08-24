package com.app.network.models

sealed class DataState<out T> {
    object Loading : DataState<Nothing>()
    data class Success<T>(val data: T) : DataState<T>()
    data class Error(val errorMessage: String) : DataState<Nothing>()
}