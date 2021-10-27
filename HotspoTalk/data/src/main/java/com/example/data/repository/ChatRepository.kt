package com.example.data.repository

import com.example.domain.entity.response.Msg

interface ChatRepository {
    suspend fun getChat(roomId: Int): Msg
}