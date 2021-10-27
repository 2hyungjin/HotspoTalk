package com.example.hotspotalk.data.repository

import com.example.hotspotalk.data.entity.response.MemberInfo
import com.example.hotspotalk.data.entity.response.RoomInfo
import com.example.hotspotalk.data.entity.response.Msg
import com.example.hotspotalk.data.entity.repuest.AccountId
import com.example.hotspotalk.data.entity.repuest.CreateRoom
import com.example.hotspotalk.data.entity.repuest.Enter
import com.example.hotspotalk.data.entity.repuest.ModifyNickname
import com.example.hotspotalk.data.entity.repuest.ModifyRoom
import retrofit2.Response

interface RoomsRepository {
    suspend fun getEnteredRooms(): Response<List<RoomInfo>>

    suspend fun getRoomsByCoordinate(latitude: Int, longitude: Int): Response<List<RoomInfo>>

    suspend fun getEnterRoom(roomId: Int, enter: Enter): Response<Msg>

    suspend fun getMember(roomId: Int): Response<List<MemberInfo>>

    suspend fun postCreateRoom(createRoom: CreateRoom): Response<Msg>

    suspend fun putModifyRoom(roomId: Int, modifyRoom: ModifyRoom): Response<Msg>

    suspend fun putInheritRoom(accountId: AccountId): Response<Msg>

    suspend fun putModifyNickname(roomId: Int, modifyNickname: ModifyNickname): Response<Msg>

    suspend fun deleteExitRoom(roomId: Int): Response<Msg>

    suspend fun deleteRemoveRoom(roomId: Int): Response<Msg>
}