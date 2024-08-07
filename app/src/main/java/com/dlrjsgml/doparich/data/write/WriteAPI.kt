package com.dlrjsgml.doparich.data.write

import retrofit2.http.Body
import retrofit2.http.POST

data class WriteDTO(
    val content: String,
    val writer: String
)

interface WriteService {
    @POST("/board/write")
    suspend fun loginUser(@Body write : WriteDTO)
}
