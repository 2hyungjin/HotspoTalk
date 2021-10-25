package com.example.data.repository

import com.example.data.datasource.ChatDataSource
import com.example.data.entity.MsgData
import com.example.domain.entity.response.Msg
import com.example.domain.repository.ChatRepository
import javax.inject.Inject

class ChatRepositoryImpl @Inject constructor(
    private val chatDataSource: ChatDataSource
) : ChatRepository {

    override suspend fun getChat(roomId: Int): Msg {
        return chatDataSource.getChat(roomId)
    }
}