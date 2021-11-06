package com.example.hotspotalk.data.service

import com.example.hotspotalk.data.entity.response.MemberInfo
import com.example.hotspotalk.data.entity.response.MessageResponse
import retrofit2.Response
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ChattingService {
    @GET("/rooms/{roomid}")
    suspend fun getMessages(
        @Path("roomid") roomId: Int,
        @Query("count") count: Int,
        @Query("start") start:Int
    ): Response<List<MessageResponse>>

    @GET("/rooms/{roomid}/member")
    suspend fun getMembers(@Path("roomid") roomId: Int): Response<List<MemberInfo>>

    @DELETE("/rooms/{roomid}/exit")
    suspend fun outChattingRoom(@Path("roomid") roomId: Int)
}