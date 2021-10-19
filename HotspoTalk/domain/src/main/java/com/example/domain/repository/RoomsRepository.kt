package com.example.domain.repository

import com.example.domain.entity.repuest.*
import com.example.domain.entity.response.MemberInfo
import com.example.domain.entity.response.RoomInfo
import retrofit2.http.Body

interface RoomsRepository {
    suspend fun getRooms(): List<RoomInfo>

    suspend fun getRooms(latitude: Int, longitude: Int): List<RoomInfo>

    suspend fun getEnterRoom(roomId: Int, enter: Enter): String

    suspend fun getMember(roomId: Int): List<MemberInfo>

    suspend fun postCreateRoom(createRoom: CreateRoom): String

    suspend fun putModifyRoom(roomId: Int, modifyRoom: ModifyRoom): String

    suspend fun putInheritRoom(accountId: AccountId): String

    suspend fun putModifyNickname(roomId: Int, modifyNickname: ModifyNickname): String

    suspend fun deleteExitRoom(roomId: Int): String

    suspend fun deleteRemoveRoom(roomId: Int): String
}