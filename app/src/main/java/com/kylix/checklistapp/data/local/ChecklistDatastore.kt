package com.kylix.checklistapp.data.local

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map

class ChecklistDatastore(
    private val context: Context
) {

    private val Context.userPreferenceDataStore: DataStore<Preferences> by preferencesDataStore(
        name = "checklist_datastore"
    )

    suspend fun saveToken(token: String) {
        context.userPreferenceDataStore.edit {
            it[TOKEN_PREF_KEY] = token
        }
    }

    suspend fun getToken(): String? = context.userPreferenceDataStore.data
        .map { it[TOKEN_PREF_KEY] }.first()

    companion object {
        val TOKEN_PREF_KEY = stringPreferencesKey("token")
    }
}