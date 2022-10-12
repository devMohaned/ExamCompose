package com.dev.exam.feature_auth.di

import com.dev.exam.core.di.DataStoreManager
import com.dev.exam.feature_auth.data.remote.AuthApi
import com.dev.exam.feature_auth.data.remote.AuthApi.Companion.BASE_URL
import com.dev.exam.feature_auth.data.repo.AuthRepoImpl
import com.dev.exam.feature_auth.domain.repo.AuthRepo
import com.dev.exam.feature_auth.domain.usecase.FindTokenUseCase
import com.dev.exam.feature_auth.domain.usecase.LoginUseCase
import com.dev.exam.feature_auth.domain.usecase.RegisterUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AuthModule {

    @Provides
    @Singleton
    fun providesLoginUseCase(authRepo: AuthRepo): LoginUseCase {
        return LoginUseCase(authRepo)
    }

    @Provides
    @Singleton
    fun providesRegisterUseCase(authRepo: AuthRepo): RegisterUseCase {
        return RegisterUseCase(authRepo)
    }


    @Provides
    @Singleton
    fun providesTokenUseCase(dataStoreManager: DataStoreManager): FindTokenUseCase {
        return FindTokenUseCase(dataStoreManager)
    }

    @Provides
    @Singleton
    fun providesAuthRepository(authApi: AuthApi, dataStoreManager: DataStoreManager): AuthRepo {
        return AuthRepoImpl(authApi, dataStoreManager)
    }


    @Provides
    @Singleton
    fun providesAuthApi(): AuthApi {

        val interceptor = HttpLoggingInterceptor()
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
        val client: OkHttpClient = OkHttpClient
            .Builder()
            .addInterceptor(interceptor).build()

        return Retrofit.Builder().baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
            .create(AuthApi::class.java)
    }


}