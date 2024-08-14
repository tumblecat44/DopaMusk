package com.dlrjsgml.doparich.data.home.boardlist.board

import kotlinx.coroutines.flow.StateFlow
import retrofit2.http.GET
import retrofit2.http.Path


data class BoardContentResponse(
    val id: Long,
    val content : String,
    val writer : String,
    val likes : Long
)

interface BoardService{
    @GET("/board")
    suspend fun getBoardContents() : List<BoardContentResponse>
}

interface BoardOneService{
    @GET("/board/{id}")
    suspend fun getBoardOneContents(@Path(value = "id") boardId : String) : BoardContentResponse
}