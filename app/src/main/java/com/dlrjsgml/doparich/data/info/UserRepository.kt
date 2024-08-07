package com.dlrjsgml.doparich.data.info

import android.content.Context
import android.provider.ContactsContract.Data
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.MutablePreferences
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

const val DataStore_NAME = "user_info"



val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = DataStore_NAME)

class UserRepository(private val context: Context) : UserController{

    companion object{
        val NAME = stringPreferencesKey("NAME")
        val USERID = stringPreferencesKey("USERID")
        val USERPW = stringPreferencesKey("USERPW")

    }

    override suspend fun saveUserInfo(userInfo: UserInfo) {
        context.dataStore.edit { users ->
            users[NAME] = userInfo.userName
            users[USERID] = userInfo.userId
            users[USERPW] = userInfo.userPw

        }
    }

    override suspend fun getUserInfo() = context.dataStore.data.map { userInfos ->
        UserInfo(
            userName = userInfos[NAME]?:"이건희",
            userId = userInfos[USERID]!!,
            userPw = userInfos[USERPW]!!
        )

    }


}
