package com.example.data.datasource

import com.example.data.base.BaseDataSource
import com.example.data.entity.repuest.*
import com.example.data.entity.response.MemberInfo
import com.example.data.entity.response.RoomInfo
import com.example.data.mapper.toEntity
import com.example.data.service.RoomService
import com.example.domain.entity.response.Msg
import javax.inject.Inject

class RoomDataSource @Inject constructor(
    override val service: RoomService
) : BaseDataSource<RoomService>() {

    suspend fun getRooms(): List<RoomInfo> {
        return getResponse(service.getRooms()).toEntity()
    }

    suspend fun getRooms(latitude: Int, longitude: Int): List<RoomInfo> {
        return getResponse(service.getRooms(latitude, longitude)).toEntity()
    }

    suspend fun getEnterRoom(roomId: Int, enter: Enter): Msg {
        return getResponse(service.getEnterRoom(roomId, enter)).toEntity()
    }

    suspend fun getMember(roomId: Int): List<MemberInfo> {
        return getResponse(service.getMember(roomId)).toEntity()
    }

    suspend fun postCreateRoom(createRoom: CreateRoom): Msg {
        return getResponse(service.postCreateRoom(createRoom)).toEntity()
    }

    suspend fun putModifyRoom(roomId: Int, modifyRoom: ModifyRoom): Msg {
        return getResponse(service.putModifyRoom(roomId, modifyRoom)).toEntity()
    }

    suspend fun putInheritRoom(accountId: AccountId): Msg {
        return getResponse(service.putInheritRoom(accountId)).toEntity()
    }

    suspend fun putModifyNickname(roomId: Int, modifyNickname: ModifyNickname): Msg {
        return getResponse(service.putModifyNickname(roomId, modifyNickname)).toEntity()
    }

    suspend fun deleteExitRoom(roomId: Int): Msg {
        return getResponse(service.deleteExitRoom(roomId)).toEntity()
    }

    suspend fun deleteRemoveRoom(roomId: Int): Msg {
        return getResponse(service.deleteRemoveRoom(roomId)).toEntity()
    }
}