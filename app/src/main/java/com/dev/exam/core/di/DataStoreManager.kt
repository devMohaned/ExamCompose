package com.dev.exam.core.di

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.dev.exam.core.di.PreferencesKeys.TOKEN
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

private const val PREF_KEY_NAME: String = "pref_settings"
private const val PREF_KEY_TOKEN: String = "pref_token"
private object PreferencesKeys {
    val TOKEN = stringPreferencesKey(PREF_KEY_TOKEN)
}

private val Context.dataStore by preferencesDataStore(PREF_KEY_NAME)

@Singleton //You can ignore this annotation as return `datastore` from `preferencesDataStore` is singletone
class DataStoreManager @Inject constructor(@ApplicationContext appContext: Context) {

    private val settingsDataStore = appContext.dataStore

    suspend fun setToken(token: String) {
        settingsDataStore.edit { settings ->
            settings[PreferencesKeys.TOKEN] = token
        }
    }
    val tokenFlow: Flow<Boolean> = settingsDataStore.data
        .map { preferences ->
            // No type safety.
            preferences[TOKEN] != null
        }

}