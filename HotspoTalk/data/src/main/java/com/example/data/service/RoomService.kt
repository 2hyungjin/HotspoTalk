package com.example.data.service

import com.example.data.entity.repuest.*
import com.example.data.entity.response.RoomInfo
import com.example.data.entity.MemberInfoData
import com.example.data.entity.MsgData
import com.example.data.entity.RoomInfoData
import retrofit2.Response
import retrofit2.http.*

interface RoomService {

    //들어갔던 방 목록
    @GET("rooms/")
    suspend fun getRooms(): Response<List<RoomInfoData>>

    // 들어갈 방목록
    @GET("rooms/")
    suspend fun getRooms(
        @Query("latitude") latitude: Int,
        @Query("longitude") longitude: Int
    ): Response<List<RoomInfoData>>

    @POST("rooms/")
    suspend fun postCreateRoom(@Body createRoom: CreateRoom): Response<MsgData>

    @GET("rooms/{roomid}")
    suspend fun getEnterRoom(@Path("roomid") roomId: Int, @Body enter: Enter): Response<MsgData>

    @GET("/rooms/{roomid}/member")
    suspend fun getMember(@Path("roomid") roomId: Int): Response<List<MemberInfoData>>

    @PUT("/rooms/{roomid}/edit")
    suspend fun putModifyRoom(@Path("roomid") roomId: Int, @Body modifyRoom: ModifyRoom): Response<MsgData>

    @PUT("/rooms/{roomid}/inherit")
    suspend fun putInheritRoom(@Body accountId: AccountId): Response<MsgData>

    @PUT("/rooms/{roomid}/rename")
    suspend fun putModifyNickname(@Path("roomid") roomId: Int, @Body modifyNickname: ModifyNickname): Response<MsgData>

    @DELETE("/rooms/{roomid}/exit")
    suspend fun deleteExitRoom(@Path("roomid") roomId: Int): Response<MsgData>

    @DELETE("/rooms/{roomid}")
    suspend fun deleteRemoveRoom(@Path("roomid") roomId: Int): Response<MsgData>
}