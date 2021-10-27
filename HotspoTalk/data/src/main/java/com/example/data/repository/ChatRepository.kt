package com.example.data.repository

interface ChatRepository {
    suspend fun getChat(roomId: Int): String
}