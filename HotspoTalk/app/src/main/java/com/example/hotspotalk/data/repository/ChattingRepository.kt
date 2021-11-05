package com.example.hotspotalk.data.repository

import com.example.hotspotalk.data.entity.request.MessageRequest
import com.example.hotspotalk.data.entity.response.MemberInfo
import com.example.hotspotalk.data.entity.response.MessageResponse
import retrofit2.Response

interface ChattingRepository {
    suspend fun getMessages(roomId: Int): Response<List<MessageResponse>>
    suspend fun postMessage(message:MessageRequest)
    suspend fun getMembers(roomId: Int):Response<List<MemberInfo>>
}