package com.nicoqueijo.android.todolist.data

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import kotlinx.coroutines.flow.first
import javax.inject.Inject

/**
 * A class for managing application preferences using DataStore.
 *
 * @property dataStore The [DataStore] instance used to manage application preferences.
 */
class DataStoreManager @Inject constructor(
    private val dataStore: DataStore<Preferences>,
) {

    /**
     * Sets the first launch preference.
     *
     * @param value The boolean value indicating if it is the first launch.
     */
    suspend fun setFirstLaunch(value: Boolean) {
        dataStore.edit { preferences ->
            preferences[PreferencesKeys.FIRST_LAUNCH] = value
        }
    }

    /**
     * Checks if it is the first launch of the application.
     *
     * @return `true` if it is the first launch, `false` otherwise.
     */
    suspend fun isFirstLaunch(): Boolean {
        val preferences = dataStore.data.first()
        return preferences[PreferencesKeys.FIRST_LAUNCH] ?: true
    }

    object PreferencesKeys {
        val FIRST_LAUNCH = booleanPreferencesKey("first_launch")
    }
}
