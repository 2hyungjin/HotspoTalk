package com.example.hotspotalk.data.repositoryImpl

import com.example.hotspotalk.data.entity.Message
import com.example.hotspotalk.data.service.RoomService
import com.example.hotspotalk.data.entity.response.MemberInfo
import com.example.hotspotalk.data.entity.response.RoomInfo
import com.example.hotspotalk.data.entity.request.AccountId
import com.example.hotspotalk.data.entity.request.CreateRoom
import com.example.hotspotalk.data.entity.request.Enter
import com.example.hotspotalk.data.entity.request.ModifyNickname
import com.example.hotspotalk.data.entity.request.ModifyRoom
import com.example.hotspotalk.data.repository.RoomsRepository
import retrofit2.Response
import javax.inject.Inject

class RoomsRepositoryImpl @Inject constructor(
    private val service: RoomService
) : RoomsRepository {
    override suspend fun getEnteredRooms(): Response<List<RoomInfo>> {
        return service.getRooms()
    }

    override suspend fun getRoomsByCoordinate(latitude: Double, longitude: Double): Response<List<RoomInfo>> {
        return service.getRooms(latitude, longitude)
    }

    override suspend fun postEnterRoom(roomId: Int, enter: Enter): Response<Any?> {
        return service.postEnterRoom(roomId, enter)
    }

    override suspend fun getMember(roomId: Int): Response<List<MemberInfo>> {
        return service.getMember(roomId)
    }

    override suspend fun postCreateRoom(createRoom: CreateRoom): Response<Any?> {
        return service.postCreateRoom(createRoom)
    }

    override suspend fun putModifyRoom(roomId: Int, modifyRoom: ModifyRoom): Response<Any?> {
        return service.putModifyRoom(roomId, modifyRoom)
    }

    override suspend fun putInheritRoom(accountId: AccountId): Response<Any?> {
        return service.putInheritRoom(accountId)
    }

    override suspend fun putModifyNickname(roomId: Int, modifyNickname: ModifyNickname): Response<Any?> {
        return service.putModifyNickname(roomId, modifyNickname)
    }

    override suspend fun deleteExitRoom(roomId: Int): Response<Message> {
        return service.deleteExitRoom(roomId)
    }

    override suspend fun deleteRemoveRoom(roomId: Int): Response<Message> {
        return service.deleteRemoveRoom(roomId)
    }


}