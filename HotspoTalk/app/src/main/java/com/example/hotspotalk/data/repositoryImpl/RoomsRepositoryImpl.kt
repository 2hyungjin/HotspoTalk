package com.example.hotspotalk.data.repositoryImpl

import com.example.hotspotalk.data.entity.Message
import com.example.hotspotalk.data.service.RoomService
import com.example.hotspotalk.data.entity.response.MemberInfo
import com.example.hotspotalk.data.entity.response.RoomInfo
import com.example.hotspotalk.data.entity.repuest.AccountId
import com.example.hotspotalk.data.entity.repuest.CreateRoom
import com.example.hotspotalk.data.entity.repuest.Enter
import com.example.hotspotalk.data.entity.repuest.ModifyNickname
import com.example.hotspotalk.data.entity.repuest.ModifyRoom
import com.example.hotspotalk.data.entity.response.MessageResponse
import com.example.hotspotalk.data.repository.RoomsRepository
import retrofit2.Response
import javax.inject.Inject

class RoomsRepositoryImpl @Inject constructor(
    private val service: RoomService
) : RoomsRepository {
    override suspend fun getEnteredRooms(): Response<List<RoomInfo>> {
        return service.getRooms()
    }

    override suspend fun getRoomsByCoordinate(latitude: Int, longitude: Int): Response<List<RoomInfo>> {
        return service.getRooms(latitude, longitude)
    }

    override suspend fun postEnterRoom(roomId: Int, enter: Enter): Response<Message> {
        return service.postEnterRoom(roomId, enter)
    }

    override suspend fun getMember(roomId: Int): Response<List<MemberInfo>> {
        return service.getMember(roomId)
    }

    override suspend fun postCreateRoom(createRoom: CreateRoom): Response<Message> {
        return service.postCreateRoom(createRoom)
    }

    override suspend fun putModifyRoom(roomId: Int, modifyRoom: ModifyRoom): Response<Message> {
        return service.putModifyRoom(roomId, modifyRoom)
    }

    override suspend fun putInheritRoom(accountId: AccountId): Response<Message> {
        return service.putInheritRoom(accountId)
    }

    override suspend fun putModifyNickname(roomId: Int, modifyNickname: ModifyNickname): Response<Message> {
        return service.putModifyNickname(roomId, modifyNickname)
    }

    override suspend fun deleteExitRoom(roomId: Int): Response<Message> {
        return service.deleteExitRoom(roomId)
    }

    override suspend fun deleteRemoveRoom(roomId: Int): Response<Message> {
        return service.deleteRemoveRoom(roomId)
    }


}