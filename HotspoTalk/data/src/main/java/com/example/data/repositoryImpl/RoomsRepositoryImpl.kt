package com.example.data.repositoryImpl

import com.example.data.datasource.RoomDataSource
import com.example.data.entity.repuest.*
import com.example.data.entity.response.MemberInfo
import com.example.data.entity.response.RoomInfo
import com.example.data.repository.RoomsRepository
import com.example.domain.entity.response.Msg
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

    override suspend fun getEnterRoom(roomId: Int, enter: Enter): Msg {
        return dataSource.getEnterRoom(roomId, enter)
    }

    override suspend fun getMember(roomId: Int): List<MemberInfo> {
        return dataSource.getMember(roomId)
    }

    override suspend fun postCreateRoom(createRoom: CreateRoom): Msg {
        return dataSource.postCreateRoom(createRoom)
    }

    override suspend fun putModifyRoom(roomId: Int, modifyRoom: ModifyRoom): Msg {
        return dataSource.putModifyRoom(roomId, modifyRoom)
    }

    override suspend fun putInheritRoom(accountId: AccountId): Msg {
        return dataSource.putInheritRoom(accountId)
    }

    override suspend fun putModifyNickname(roomId: Int, modifyNickname: ModifyNickname): Msg {
        return dataSource.putModifyNickname(roomId, modifyNickname)
    }

    override suspend fun deleteExitRoom(roomId: Int): Msg {
        return dataSource.deleteExitRoom(roomId)
    }

    override suspend fun deleteRemoveRoom(roomId: Int): Msg {
        return dataSource.deleteRemoveRoom(roomId)
    }


}