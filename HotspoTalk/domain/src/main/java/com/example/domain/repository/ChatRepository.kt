package com.example.domain.repository

import retrofit2.http.GET
import retrofit2.http.Path

interface ChatRepository {
    suspend fun getChat(roomId: Int): String
}