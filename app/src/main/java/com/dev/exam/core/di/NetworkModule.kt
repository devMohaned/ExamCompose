package com.dev.exam.core.di

import com.dev.exam.core.util.retrofit.RequestInterceptor
import com.dev.exam.core.util.retrofit.ResultCallAdapterFactory
import com.dev.exam.feature_auth.data.remote.AuthApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {


    @Provides
    @Singleton
    fun providesHttpInterceptor(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
    }

    @Provides
    @Singleton
    fun providesHttpClient(
        requestInterceptor: RequestInterceptor,
        loggingInterceptor: HttpLoggingInterceptor
    ): OkHttpClient {

        return OkHttpClient
            .Builder()
            .addInterceptor(requestInterceptor)
            .addInterceptor(loggingInterceptor)
            .build()
    }

    @Provides
    @Singleton
    fun providesRetrofit(client: OkHttpClient): Retrofit {
        return Retrofit.Builder().baseUrl(AuthApi.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(ResultCallAdapterFactory())
            .client(client)
            .build()
    }
}