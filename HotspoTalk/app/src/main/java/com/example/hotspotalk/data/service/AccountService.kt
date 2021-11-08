package com.example.hotspotalk.data.service

import com.example.hotspotalk.data.entity.Message
import com.example.hotspotalk.data.entity.request.Ban
import com.example.hotspotalk.data.entity.request.Login
import com.example.hotspotalk.data.entity.request.SignUp
import com.example.hotspotalk.data.entity.response.Token
import retrofit2.Response
import retrofit2.http.*

interface AccountService {
    @POST("account/login")
    suspend fun postLogin(@Body login: Login): Response<Token>

    @POST("account/signUp")
    suspend fun postSignUp(@Body signUp: SignUp): Response<Token>

    @GET("account/{id}")
    suspend fun getId(@Path("id") id: String): Response<Message>

    @GET("/account/ban")
    suspend fun deleteBan(@Body ban: Ban): Response<Message>

    @POST("account/autoLogin")
    suspend fun postAutoLogin(): Response<Any?>

    @FormUrlEncoded
    @PUT("account/device")
    suspend fun postDevToken(@Field("deviceToken") deviceToken: String): Response<Any?>
}
