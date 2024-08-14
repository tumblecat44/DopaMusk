package com.dlrjsgml.doparich.data.datastore

import com.dlrjsgml.doparich.data.datastore.model.UserId
import kotlinx.coroutines.flow.Flow

interface Abstract {
    suspend fun saveUserId(userId: UserId)

    suspend fun getUserId(): Flow<UserId>
}