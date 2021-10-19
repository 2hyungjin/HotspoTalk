package com.example.data.datasource

import com.example.data.base.BaseDataSource
import com.example.data.service.ChatService
import javax.inject.Inject

class ChatDataSource @Inject constructor(
    override val service: ChatService
) : BaseDataSource<ChatService>() {

    suspend fun getChat(roomId: Int): String {
        return getResponse(service.getChat(roomId))
    }
}