package com.example.data.datasource

import com.example.data.base.BaseDataSource
import com.example.data.mapper.toEntity
import com.example.data.service.RoomService
import com.example.domain.entity.repuest.*
import com.example.domain.entity.response.MemberInfo
import com.example.domain.entity.response.RoomInfo
import javax.inject.Inject

class RoomDataSource @Inject constructor(
    override val service: RoomService
) : BaseDataSource<RoomService>() {

    suspend fun getRooms(): List<RoomInfo> {
        return getResponse(service.getRooms()).toEntity()
    }

    suspend fun getRooms(latitude: Int, longitude: Int): List<RoomInfo> {
        return getResponse(service.getRooms(latitude, longitude))
    }

    suspend fun getEnterRoom(roomId: Int, enter: Enter): String {
        return getResponse(service.getEnterRoom(roomId, enter))
    }

    suspend fun getMember(roomId: Int): List<MemberInfo> {
        return getResponse(service.getMember(roomId)).toEntity()
    }

    suspend fun postCreateRoom(createRoom: CreateRoom): String {
        return getResponse(service.postCreateRoom(createRoom))
    }

    suspend fun putModifyRoom(roomId: Int, modifyRoom: ModifyRoom): String {
        return getResponse(service.putModifyRoom(roomId, modifyRoom))
    }

    suspend fun putInheritRoom(accountId: AccountId): String {
        return getResponse(service.putInheritRoom(accountId))
    }

    suspend fun putModifyNickname(roomId: Int, modifyNickname: ModifyNickname): String {
        return getResponse(service.putModifyNickname(roomId, modifyNickname))
    }

    suspend fun deleteExitRoom(roomId: Int): String {
        return getResponse(service.deleteExitRoom(roomId))
    }

    suspend fun deleteRemoveRoom(roomId: Int): String {
        return getResponse(service.deleteRemoveRoom(roomId))
    }
}