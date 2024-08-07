package com.dlrjsgml.doparich.remote

import com.dlrjsgml.doparich.data.account.AccountService
import com.dlrjsgml.doparich.data.info.userInfoApiService
import com.dlrjsgml.doparich.data.login.UserService
import com.dlrjsgml.doparich.data.write.WriteService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {
    private const val BASE_URL = "http://192.168.123.181:8080"
    val instance: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
    val userService: UserService by lazy { instance.create(UserService::class.java) }
    val accountService: AccountService by lazy { instance.create(AccountService::class.java) }
    val infoService: userInfoApiService by lazy { instance.create(userInfoApiService::class.java) }
    val writeService : WriteService by lazy { instance.create(WriteService::class.java) }

}
