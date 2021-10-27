package com.example.hotspotalk.data.service

import com.example.hotspotalk.data.entity.Message
import com.example.hotspotalk.data.entity.response.MemberInfo
import com.example.hotspotalk.data.entity.response.RoomInfo
import com.example.hotspotalk.data.entity.repuest.AccountId
import com.example.hotspotalk.data.entity.repuest.CreateRoom
import com.example.hotspotalk.data.entity.repuest.Enter
import com.example.hotspotalk.data.entity.repuest.ModifyNickname
import com.example.hotspotalk.data.entity.repuest.ModifyRoom
import com.example.hotspotalk.data.entity.response.MessageResponse
import retrofit2.Response
import retrofit2.http.*

interface RoomService {

    //들어갔던 방 목록
    @GET("rooms/")
    suspend fun getRooms(): Response<List<RoomInfo>>

    // 들어갈 방목록
    @GET("rooms/")
    suspend fun getRooms(
        @Query("latitude") latitude: Int,
        @Query("longitude") longitude: Int
    ): Response<List<RoomInfo>>

    @POST("rooms/")
    suspend fun postCreateRoom(@Body createRoom: CreateRoom): Response<MessageResponse>

    @GET("rooms/{roomid}")
    suspend fun postEnterRoom(@Path("roomid") roomId: Int, @Body enter: Enter): Response<MessageResponse>

    @GET("/rooms/{roomid}/member")
    suspend fun getMember(@Path("roomid") roomId: Int): Response<List<MemberInfo>>

    @PUT("/rooms/{roomid}/edit")
    suspend fun putModifyRoom(@Path("roomid") roomId: Int, @Body modifyRoom: ModifyRoom): Response<MessageResponse>

    @PUT("/rooms/{roomid}/inherit")
    suspend fun putInheritRoom(@Body accountId: AccountId): Response<MessageResponse>

    @PUT("/rooms/{roomid}/rename")
    suspend fun putModifyNickname(@Path("roomid") roomId: Int, @Body modifyNickname: ModifyNickname): Response<MessageResponse>

    @DELETE("/rooms/{roomid}/exit")
    suspend fun deleteExitRoom(@Path("roomid") roomId: Int): Response<MessageResponse>

    @DELETE("/rooms/{roomid}")
    suspend fun deleteRemoveRoom(@Path("roomid") roomId: Int): Response<MessageResponse>
}