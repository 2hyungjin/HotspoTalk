package com.example.hotspotalk.data.repository

import com.example.hotspotalk.data.entity.response.Msg
import retrofit2.Response

interface ChatRepository {
    suspend fun getChat(roomId: Int): Response<Msg>
}