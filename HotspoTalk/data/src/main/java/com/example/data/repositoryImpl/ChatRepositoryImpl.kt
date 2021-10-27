package com.example.data.repositoryImpl

import com.example.data.datasource.ChatDataSource
import com.example.data.repository.ChatRepository
import com.example.domain.entity.response.Msg
import javax.inject.Inject

class ChatRepositoryImpl @Inject constructor(
    private val chatDataSource: ChatDataSource
) : ChatRepository {

    override suspend fun getChat(roomId: Int): Msg {
        return chatDataSource.getChat(roomId)
    }
}