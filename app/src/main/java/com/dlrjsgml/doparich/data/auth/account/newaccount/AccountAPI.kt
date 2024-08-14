package com.dlrjsgml.doparich.data.auth.account.newaccount

import com.dlrjsgml.doparich.data.response.SuccessResponse
import retrofit2.http.Body
import retrofit2.http.POST


data class NewUserDTO(
    val userName : String,
    val userId : String,
    val userPw : String
)



interface AccountService{
    @POST("/users")
    suspend fun accountUser(@Body newUser : NewUserDTO) : SuccessResponse
}