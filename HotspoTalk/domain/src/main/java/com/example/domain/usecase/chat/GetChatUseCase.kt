package com.example.domain.usecase.chat

import com.example.domain.base.ParamsUseCase
import com.example.domain.entity.response.Msg
import com.example.domain.repository.ChatRepository
import javax.inject.Inject

class GetChatUseCase @Inject constructor(
    private val chatRepository: ChatRepository
) : ParamsUseCase<GetChatUseCase.Params, Msg>() {

    override suspend fun buildUseCase(params: Params): Msg {
        return chatRepository.getChat(params.roomId)
    }

    data class Params(
        val roomId: Int
    )
}