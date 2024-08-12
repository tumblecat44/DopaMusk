package com.dlrjsgml.doparich.data.account.login


import com.dlrjsgml.doparich.data.response.SuccessResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

data class UserDTO(
    val userId: String,
    val userPw: String
)


interface UserService {
    @POST("/users/login")
    suspend fun loginUser(@Body user: UserDTO) : SuccessResponse
}

