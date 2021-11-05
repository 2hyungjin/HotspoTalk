package com.example.hotspotalk.data.service

import com.example.hotspotalk.data.entity.Message
import com.example.hotspotalk.data.entity.request.Ban
import com.example.hotspotalk.data.entity.request.Login
import com.example.hotspotalk.data.entity.request.SignUp
import com.example.hotspotalk.data.entity.response.Token
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface AccountService {
    @POST("account/login")
    suspend fun postLogin(@Body login: Login): Response<Token>

    @POST("account/signUp")
    suspend fun postSignUp(@Body signUp: SignUp): Response<Token>

    @GET("account/{id}")
    suspend fun getId(@Path("id") id: String): Response<Message>

    @GET("/account/ban")
    suspend fun deleteBan(@Body ban: Ban): Response<Message>
}
