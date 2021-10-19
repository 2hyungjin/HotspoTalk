package com.example.data.service

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface ChatService {
    @GET("/chat/{roomid}")
    suspend fun getChat(@Path("roomid") roomId: Int): Response<String>
}