package com.example.data.repository

import com.example.data.entity.repuest.*
import com.example.data.entity.response.MemberInfo
import com.example.data.entity.response.RoomInfo

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