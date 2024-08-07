package com.dlrjsgml.doparich.data.info

import kotlinx.coroutines.flow.Flow


interface UserController {
    suspend fun saveUserInfo(userInfo: UserInfo)
    suspend fun getUserInfo(): Flow<UserInfo>

}