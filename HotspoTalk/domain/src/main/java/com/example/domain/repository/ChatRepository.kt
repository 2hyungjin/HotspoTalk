package com.example.domain.repository

import com.example.domain.entity.response.Msg

interface ChatRepository {
    suspend fun getChat(roomId: Int): Msg
}