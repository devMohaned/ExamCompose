package com.dev.exam.core.util.retrofit

import com.dev.exam.core.data.local.SharedPrefs
import com.dev.exam.core.di.DataStoreManager
import okhttp3.Interceptor
import okhttp3.Response
import okhttp3.internal.platform.android.AndroidLogHandler.setLevel
import okhttp3.logging.HttpLoggingInterceptor
import javax.inject.Inject

class RequestInterceptor @Inject constructor(private val pref: SharedPrefs) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val token = pref.getToken()
        val newRequest = chain.request().newBuilder()
            .addHeader("Authorization", "Bearer $token")
            .build()
        return chain.proceed(newRequest)
    }
}