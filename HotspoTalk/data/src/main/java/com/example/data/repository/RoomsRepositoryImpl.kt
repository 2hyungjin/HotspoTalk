package com.example.data.repository

import com.example.data.datasource.RoomDataSource
import com.example.domain.entity.repuest.*
import com.example.domain.entity.response.MemberInfo
import com.example.domain.entity.response.RoomInfo
import com.example.domain.repository.RoomsRepository
import javax.inject.Inject

class RoomsRepositoryImpl @Inject constructor(
    private val dataSource: RoomDataSource
) : RoomsRepository {
    override suspend fun getRooms(): List<RoomInfo> {
        return dataSource.getRooms()
    }

    override suspend fun getRooms(latitude: Int, longitude: Int): List<RoomInfo> {
        return dataSource.getRooms(latitude, longitude)
    }

    override suspend fun getEnterRoom(roomId: Int, enter: Enter): String {
        return dataSource.getEnterRoom(roomId, enter)
    }

    override suspend fun getMember(roomId: Int): List<MemberInfo> {
        return dataSource.getMember(roomId)
    }

    override suspend fun postCreateRoom(createRoom: CreateRoom): String {
        return dataSource.postCreateRoom(createRoom)
    }

    override suspend fun putModifyRoom(roomId: Int, modifyRoom: ModifyRoom): String {
        return dataSource.putModifyRoom(roomId, modifyRoom)
    }

    override suspend fun putInheritRoom(accountId: AccountId): String {
        return dataSource.putInheritRoom(accountId)
    }

    override suspend fun putModifyNickname(roomId: Int, modifyNickname: ModifyNickname): String {
        return dataSource.putModifyNickname(roomId, modifyNickname)
    }

    override suspend fun deleteExitRoom(roomId: Int): String {
        return dataSource.deleteExitRoom(roomId)
    }

    override suspend fun deleteRemoveRoom(roomId: Int): String {
        return dataSource.deleteRemoveRoom(roomId)
    }


}