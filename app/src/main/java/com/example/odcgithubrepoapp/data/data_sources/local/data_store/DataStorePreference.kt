package com.example.odcgithubrepoapp.data.data_sources.local.data_store

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import com.example.odcgithubrepoapp.data.data_sources.local.data_store.DataStorePreference.Companion.PreferenceKeys.isFirstTimeKey
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class DataStorePreference @Inject constructor(
    private val context: Context
) {
    companion object {
        private object PreferenceKeys {
            val isFirstTimeKey = booleanPreferencesKey("isFirstTime")
        }
        private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(
            name = ""
        )
    }

    suspend fun saveIsFirstTimeEnterApp(isFirstTime:Boolean){
        context.dataStore.edit { mutablePreferences ->
            mutablePreferences[isFirstTimeKey] = isFirstTime
        }
    }

    fun readIsFirstTimeEnterApp(): Flow<Boolean> {
        return context.dataStore.data
            .map { preferences ->
                preferences[isFirstTimeKey] ?: true
            }
    }
}