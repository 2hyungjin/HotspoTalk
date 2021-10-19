package com.example.data.service

import com.example.domain.entity.repuest.Ban
import com.example.domain.entity.repuest.Login
import com.example.domain.entity.response.Token
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface AccountService {
    @POST("account/login")
    suspend fun postLogin(@Body login: Login): Response<String>

    @POST("account/signUp")
    suspend fun postSignUp(@Body login: Login): Response<String>

    @GET("account/{id}")
    suspend fun getId(@Path("id") id: String): Response<String>

    @GET("/account/ban")
    suspend fun deleteBan(@Body ban: Ban): Response<String>
}
