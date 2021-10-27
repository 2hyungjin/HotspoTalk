package com.example.data.repository

import com.example.data.entity.repuest.*
import com.example.data.entity.response.MemberInfo
import com.example.data.entity.response.RoomInfo
import com.example.domain.entity.response.Msg
import retrofit2.http.Body

interface RoomsRepository {
    suspend fun getRooms(): List<RoomInfo>

    suspend fun getRooms(latitude: Int, longitude: Int): List<RoomInfo>

    suspend fun getEnterRoom(roomId: Int, enter: Enter): Msg

    suspend fun getMember(roomId: Int): List<MemberInfo>

    suspend fun postCreateRoom(createRoom: CreateRoom): Msg

    suspend fun putModifyRoom(roomId: Int, modifyRoom: ModifyRoom): Msg

    suspend fun putInheritRoom(accountId: AccountId): Msg

    suspend fun putModifyNickname(roomId: Int, modifyNickname: ModifyNickname): Msg

    suspend fun deleteExitRoom(roomId: Int): Msg

    suspend fun deleteRemoveRoom(roomId: Int): Msg
}