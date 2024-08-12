package com.dlrjsgml.doparich.data.home.my

import com.dlrjsgml.doparich.data.account.newaccount.NewUserDTO
import retrofit2.http.GET
import retrofit2.http.Path


interface MyService {
    @GET("/users/{userId}")
    suspend fun userInfo(@Path(value = "userId")userIds : String) : NewUserDTO
}
