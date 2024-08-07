package com.dlrjsgml.doparich.data.board

import retrofit2.http.GET


data class BoardContentResponse(
    val content : String,
    val writer : String,
    val likes : Long
)

interface BoardService{
    @GET("/board")
    suspend fun getBoardContents() : List<BoardContentResponse>
}