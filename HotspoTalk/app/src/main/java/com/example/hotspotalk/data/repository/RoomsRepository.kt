package com.example.hotspotalk.data.repository

import com.example.hotspotalk.data.entity.Message
import com.example.hotspotalk.data.entity.response.MemberInfo
import com.example.hotspotalk.data.entity.response.RoomInfo
import com.example.hotspotalk.data.entity.repuest.AccountId
import com.example.hotspotalk.data.entity.repuest.CreateRoom
import com.example.hotspotalk.data.entity.repuest.Enter
import com.example.hotspotalk.data.entity.repuest.ModifyNickname
import com.example.hotspotalk.data.entity.repuest.ModifyRoom
import retrofit2.Response

interface RoomsRepository {
    suspend fun getEnteredRooms(): Response<List<RoomInfo>>

    suspend fun getRoomsByCoordinate(latitude: Double, longitude: Double): Response<List<RoomInfo>>

    suspend fun postEnterRoom(enter: Enter): Response<Any?>

    suspend fun getMember(roomId: Int): Response<List<MemberInfo>>

    suspend fun postCreateRoom(createRoom: CreateRoom): Response<Any?>

    suspend fun putModifyRoom(roomId: Int, modifyRoom: ModifyRoom): Response<Any?>

    suspend fun putInheritRoom(accountId: AccountId): Response<Any?>

    suspend fun putModifyNickname(roomId: Int, modifyNickname: ModifyNickname): Response<Any?>

    suspend fun deleteExitRoom(roomId: Int): Response<Message>

    suspend fun deleteRemoveRoom(roomId: Int): Response<Message>
}