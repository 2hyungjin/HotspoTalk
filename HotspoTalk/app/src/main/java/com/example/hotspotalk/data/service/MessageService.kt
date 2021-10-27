package com.example.hotspotalk.data.service

import com.example.hotspotalk.data.entity.response.MessageResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface MessageService {
    @GET("/chat/{roomid}")
    suspend fun getMessages(@Path("roomid") roomId: Int): Response<List<MessageResponse>>
}