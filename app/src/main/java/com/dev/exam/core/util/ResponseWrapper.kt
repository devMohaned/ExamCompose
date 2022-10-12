package com.dev.exam.core.util

data class ResponseWrapper<T>(val code: Int, val message: String, val errors: List<String>, val data: T) {
}