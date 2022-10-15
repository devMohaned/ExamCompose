package com.dev.exam.feature_auth.di

import com.dev.exam.core.data.local.SharedPrefs
import com.dev.exam.feature_auth.data.remote.AuthApi
import com.dev.exam.feature_auth.data.repo.AuthRepoImpl
import com.dev.exam.feature_auth.domain.repo.AuthRepo
import com.dev.exam.feature_auth.domain.usecase.IsTokenFoundUseCase
import com.dev.exam.feature_auth.domain.usecase.LoginUseCase
import com.dev.exam.feature_auth.domain.usecase.RegisterUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
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
    fun providesTokenUseCase(sharedPrefs: SharedPrefs): IsTokenFoundUseCase {
        return IsTokenFoundUseCase(sharedPrefs)
    }

    @Provides
    @Singleton
    fun providesAuthRepository(authApi: AuthApi, sharedPrefs: SharedPrefs): AuthRepo {
        return AuthRepoImpl(authApi, sharedPrefs)
    }


    @Provides
    @Singleton
    fun providesAuthApi(retrofit: Retrofit): AuthApi {
            return retrofit.create(AuthApi::class.java)
    }



}