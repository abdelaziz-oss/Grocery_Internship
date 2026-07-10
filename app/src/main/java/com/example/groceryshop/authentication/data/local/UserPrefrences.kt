package com.example.groceryshop.authentication.data.local

import android.content.Context
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

val Context.dataStore by preferencesDataStore(name = "settings")

class UserPreferences(private val context: Context) {
    companion object {
        val HAS_LOGGED_IN = booleanPreferencesKey("has_logged_in")
    }

    val hasLoggedIn: Flow<Boolean> = context.dataStore.data.map { prefs ->
        prefs[HAS_LOGGED_IN] ?: false
    }

    suspend fun setLoggedIn(value: Boolean) {
        context.dataStore.edit { prefs -> prefs[HAS_LOGGED_IN] = value }
    }
}