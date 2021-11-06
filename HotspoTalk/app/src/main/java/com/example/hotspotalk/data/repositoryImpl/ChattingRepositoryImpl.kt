package com.example.hotspotalk.data.repositoryImpl

import com.example.hotspotalk.data.entity.request.MessageRequest
import com.example.hotspotalk.data.entity.response.MemberInfo
import com.example.hotspotalk.data.entity.response.MessageResponse
import com.example.hotspotalk.data.repository.ChattingRepository
import com.example.hotspotalk.data.service.ChattingService
import retrofit2.Response
import javax.inject.Inject

class ChattingRepositoryImpl @Inject constructor(
    private val service: ChattingService
) : ChattingRepository {
    override suspend fun getMessages(
        roomId: Int,
        count: Int,
        start: Int
    ): Response<List<MessageResponse>> {
        return service.getMessages(roomId, count, start)
    }

    override suspend fun postMessage(message: MessageRequest) {

    }

    override suspend fun getMembers(roomId: Int): Response<List<MemberInfo>> {
        return service.getMembers(roomId)
    }

    override suspend fun outChattingRoom(roomId: Int) {
        service.outChattingRoom(roomId)
    }

}