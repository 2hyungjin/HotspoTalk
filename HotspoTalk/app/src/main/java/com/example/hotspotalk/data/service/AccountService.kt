package com.example.hotspotalk.data.service

import com.example.hotspotalk.data.entity.repuest.Ban
import com.example.hotspotalk.data.entity.repuest.Login
import com.example.hotspotalk.data.entity.response.Token
import com.example.hotspotalk.data.entity.response.Message
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface AccountService {
    @POST("account/login")
    suspend fun postLogin(@Body login: Login): Response<Token>

    @POST("account/signUp")
    suspend fun postSignUp(@Body login: Login): Response<Token>

    @GET("account/{id}")
    suspend fun getId(@Path("id") id: String): Response<Message>

    @GET("/account/ban")
    suspend fun deleteBan(@Body ban: Ban): Response<Message>
}
