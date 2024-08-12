package com.dlrjsgml.doparich.data.datastore

import kotlinx.coroutines.flow.Flow

interface Abstract {
    suspend fun saveUserId(userId: UserId)

    suspend fun getUserId(): Flow<UserId>
}