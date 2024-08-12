package com.dlrjsgml.doparich.remote

import com.dlrjsgml.doparich.data.account.newaccount.AccountService
import com.dlrjsgml.doparich.data.home.boardlist.board.BoardService
import com.dlrjsgml.doparich.data.account.login.UserService
import com.dlrjsgml.doparich.data.home.my.MyService
import com.dlrjsgml.doparich.data.home.boardlist.write.WriteService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {
    private const val BASE_URL = "http://10.80.162.20:8080"
    val instance: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
    val userService: UserService by lazy { instance.create(UserService::class.java) }
    val accountService: AccountService by lazy { instance.create(AccountService::class.java) }
//    val infoService: userInfoApiService by lazy { instance.create(userInfoApiService::class.java) }
    val writeService : WriteService by lazy { instance.create(WriteService::class.java) }
    val boardService : BoardService by lazy { instance.create(BoardService::class.java) }
    val myService : MyService by lazy { instance.create(MyService::class.java) }

}
