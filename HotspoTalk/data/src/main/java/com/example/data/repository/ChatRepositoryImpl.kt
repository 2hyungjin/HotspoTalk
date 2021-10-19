package com.example.data.repository

import com.example.data.datasource.ChatDataSource
import com.example.domain.repository.ChatRepository
import javax.inject.Inject

class ChatRepositoryImpl @Inject constructor(
    private val chatDataSource: ChatDataSource
) : ChatRepository {

    override suspend fun getChat(roomId: Int): String {
        return chatDataSource.getChat(roomId)
    }
}