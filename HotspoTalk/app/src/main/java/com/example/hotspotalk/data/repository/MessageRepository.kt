package com.example.hotspotalk.data.repository

import com.example.hotspotalk.data.entity.repuest.MessageRequest
import com.example.hotspotalk.data.entity.response.MessageResponse
import retrofit2.Response

interface MessageRepository {
    suspend fun getMessages(roomId: Int): Response<List<MessageResponse>>
    suspend fun postMessage(message:MessageRequest)
}