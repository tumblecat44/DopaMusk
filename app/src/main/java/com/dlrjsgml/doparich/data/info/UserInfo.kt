package com.dlrjsgml.doparich.data.info

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

data class UserInfo(
    val userName : String,
    val userId : String,
    val userPw : String
)


interface userInfoApiService{
    @GET("/users/{userId}")
    suspend fun getUserInfo(@Path("userId") userId : String) :UserInfo
}
