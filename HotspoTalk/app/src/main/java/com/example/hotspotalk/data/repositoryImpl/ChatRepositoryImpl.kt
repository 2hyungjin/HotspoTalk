package com.example.hotspotalk.data.repositoryImpl

import com.example.hotspotalk.data.repository.ChatRepository
import com.example.hotspotalk.data.service.ChatService
import com.example.hotspotalk.data.entity.response.Msg
import retrofit2.Response
import javax.inject.Inject

class ChatRepositoryImpl @Inject constructor(
    private val service: ChatService
) : ChatRepository {

    override suspend fun getChat(roomId: Int): Response<Msg> {
        return service.getChat(roomId)
    }
}