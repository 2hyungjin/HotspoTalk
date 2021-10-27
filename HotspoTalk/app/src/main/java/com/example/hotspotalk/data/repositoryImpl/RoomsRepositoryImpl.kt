package com.example.hotspotalk.data.repositoryImpl

import com.example.hotspotalk.data.service.RoomService
import com.example.hotspotalk.data.entity.response.MemberInfo
import com.example.hotspotalk.data.entity.response.RoomInfo
import com.example.hotspotalk.data.entity.response.Msg
import com.example.hotspotalk.data.entity.repuest.AccountId
import com.example.hotspotalk.data.entity.repuest.CreateRoom
import com.example.hotspotalk.data.entity.repuest.Enter
import com.example.hotspotalk.data.entity.repuest.ModifyNickname
import com.example.hotspotalk.data.entity.repuest.ModifyRoom
import com.example.hotspotalk.data.repository.RoomsRepository
import retrofit2.Response
import javax.inject.Inject

class RoomsRepositoryImpl @Inject constructor(
    private val service: RoomService
) : RoomsRepository {
    override suspend fun getRooms(): Response<List<RoomInfo>> {
        return service.getRooms()
    }

    override suspend fun getRooms(latitude: Int, longitude: Int): Response<List<RoomInfo>> {
        return service.getRooms(latitude, longitude)
    }

    override suspend fun postEnterRoom(roomId: Int, enter: Enter): Response<Msg> {
        return service.postEnterRoom(roomId, enter)
    }

    override suspend fun getMember(roomId: Int): Response<List<MemberInfo>> {
        return service.getMember(roomId)
    }

    override suspend fun postCreateRoom(createRoom: CreateRoom): Response<Msg> {
        return service.postCreateRoom(createRoom)
    }

    override suspend fun putModifyRoom(roomId: Int, modifyRoom: ModifyRoom): Response<Msg> {
        return service.putModifyRoom(roomId, modifyRoom)
    }

    override suspend fun putInheritRoom(accountId: AccountId): Response<Msg> {
        return service.putInheritRoom(accountId)
    }

    override suspend fun putModifyNickname(roomId: Int, modifyNickname: ModifyNickname): Response<Msg> {
        return service.putModifyNickname(roomId, modifyNickname)
    }

    override suspend fun deleteExitRoom(roomId: Int): Response<Msg> {
        return service.deleteExitRoom(roomId)
    }

    override suspend fun deleteRemoveRoom(roomId: Int): Response<Msg> {
        return service.deleteRemoveRoom(roomId)
    }


}