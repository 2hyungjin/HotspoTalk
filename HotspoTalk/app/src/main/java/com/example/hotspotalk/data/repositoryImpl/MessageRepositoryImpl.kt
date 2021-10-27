package com.example.hotspotalk.data.repositoryImpl

import com.example.hotspotalk.data.entity.repuest.MessageRequest
import com.example.hotspotalk.data.repository.MessageRepository
import com.example.hotspotalk.data.service.MessageService
import com.example.hotspotalk.data.entity.response.MessageResponse
import retrofit2.Response
import javax.inject.Inject

class MessageRepositoryImpl @Inject constructor(
    private val service: MessageService
) : MessageRepository {
    override suspend fun getMessages(roomId: Int): Response<List<MessageResponse>> {
        return service.getMessages(roomId)
    }

    override suspend fun postMessage(message: MessageRequest) {

    }

}