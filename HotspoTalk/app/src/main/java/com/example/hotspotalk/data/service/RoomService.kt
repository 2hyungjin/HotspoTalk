package com.example.hotspotalk.data.service

import com.example.hotspotalk.data.entity.Message
import com.example.hotspotalk.data.entity.response.MemberInfo
import com.example.hotspotalk.data.entity.response.RoomInfo
import com.example.hotspotalk.data.entity.request.AccountId
import com.example.hotspotalk.data.entity.request.CreateRoom
import com.example.hotspotalk.data.entity.request.Enter
import com.example.hotspotalk.data.entity.request.ModifyNickname
import com.example.hotspotalk.data.entity.request.ModifyRoom
import com.example.hotspotalk.data.entity.response.EnteredRoomInfo
import retrofit2.Response
import retrofit2.http.*

interface RoomService {

    //들어갔던 방 목록
    @GET("/rooms")
    suspend fun getRooms(): Response<List<EnteredRoomInfo>>

    // 들어갈 방목록
    @GET("/rooms")
    suspend fun getRooms(
        @Query("latitude") latitude: Double,
        @Query("longitude") longitude: Double
    ): Response<List<RoomInfo>>

    @POST("/rooms")
    suspend fun postCreateRoom(@Body createRoom: CreateRoom): Response<Any?>

    @POST("/rooms/in/{roomId}")
    suspend fun postEnterRoom(@Path("roomId") roomId: Int, @Body enter: Enter): Response<Any?>

    @GET("/rooms/{roomid}/member")
    suspend fun getMember(@Path("roomid") roomId: Int): Response<List<MemberInfo>>

    @PUT("/rooms/{roomid}/edit")
    suspend fun putModifyRoom(@Path("roomid") roomId: Int, @Body modifyRoom: ModifyRoom): Response<Any?>

    @PUT("/rooms/{roomid}/inherit")
    suspend fun putInheritRoom(@Body accountId: AccountId): Response<Any?>

    @PUT("/rooms/{roomid}/rename")
    suspend fun putModifyNickname(@Path("roomid") roomId: Int, @Body modifyNickname: ModifyNickname): Response<Any?>

    @DELETE("/rooms/{roomid}/exit")
    suspend fun deleteExitRoom(@Path("roomid") roomId: Int): Response<Message>

    @DELETE("/rooms/{roomid}")
    suspend fun deleteRemoveRoom(@Path("roomid") roomId: Int): Response<Message>
}