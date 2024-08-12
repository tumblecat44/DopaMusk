package com.dlrjsgml.doparich.data.datastore

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject

const val DataStore_NAME = "USERID"

val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = DataStore_NAME)


class ImplRepository @Inject constructor(
    @ApplicationContext private val context: Context
) : Abstract {

    companion object{
        val ID = stringPreferencesKey("ID")
    }
    init {
        CoroutineScope(Dispatchers.IO).launch{
            getUserId()

        }
    }

    override suspend fun saveUserId(userId: UserId) {
        context.dataStore.edit {ids ->
            ids[ID] = userId.id.toString()
        }
    }

    override suspend fun getUserId() = context.dataStore.data.map { id ->
        UserId(id = id[ID]?: "null")
    }
}