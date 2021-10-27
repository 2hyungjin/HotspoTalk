package com.example.hotspotalk.data.service

import com.example.hotspotalk.data.entity.response.Msg
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface ChatService {
    @GET("/chat/{roomid}")
    suspend fun getChat(@Path("roomid") roomId: Int): Response<Msg>
}