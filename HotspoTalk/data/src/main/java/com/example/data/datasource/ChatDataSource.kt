package com.example.data.datasource

import com.example.data.base.BaseDataSource
import com.example.data.mapper.toEntity
import com.example.data.service.ChatService
import com.example.domain.entity.response.Msg
import javax.inject.Inject

class ChatDataSource @Inject constructor(
    override val service: ChatService
) : BaseDataSource<ChatService>() {

    suspend fun getChat(roomId: Int): Msg {
        return getResponse(service.getChat(roomId)).toEntity()
    }
}