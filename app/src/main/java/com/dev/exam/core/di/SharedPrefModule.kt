package com.dev.exam.core.di

import android.content.Context
import com.dev.exam.core.data.local.SharedPrefs
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object SharedPrefModule {

    @Provides
    fun provideSharedPref(@ApplicationContext context: Context) : SharedPrefs{
        return SharedPrefs(context)
    }
}