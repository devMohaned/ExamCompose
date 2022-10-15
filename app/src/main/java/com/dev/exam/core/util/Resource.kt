package com.dev.exam.core.util

sealed class Resource<out T,out E> {
    data class Loading<T>(val data: T? = null) : Resource<T, Nothing>()
    data class Success<T>(val data: T? = null) : Resource<T, Nothing>()
    data class Error<E>(val data: E? = null, val message: String) : Resource<Nothing,E>()
}