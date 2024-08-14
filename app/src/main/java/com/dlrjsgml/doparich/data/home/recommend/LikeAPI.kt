package com.dlrjsgml.doparich.data.home.recommend

import retrofit2.http.GET
import retrofit2.http.PATCH
import retrofit2.http.Path

interface LikeService {
    @PATCH("/board/up/{id}")
    suspend fun upLikes(@Path(value = "id")id : String)
}
interface UnLikeService {
    @PATCH("/board/down/{id}")
    suspend fun downLikes(@Path(value = "id")id : String)
}
