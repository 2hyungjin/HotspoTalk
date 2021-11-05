package com.example.hotspotalk.data.service

import com.example.hotspotalk.data.entity.response.MemberInfo
import com.example.hotspotalk.data.entity.response.MessageResponse
import retrofit2.Response
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Path

interface ChattingService {
    @GET("/chat/{roomid}")
    suspend fun getMessages(@Path("roomid") roomId: Int): Response<List<MessageResponse>>

    @GET("/member/{roomid}")
    suspend fun getMembers(@Path("roomid") roomId: Int): Response<List<MemberInfo>>

    @DELETE("/rooms/{roomid}")
    suspend fun outChattingRoom(@Path("roomid") roomId: Int)
}