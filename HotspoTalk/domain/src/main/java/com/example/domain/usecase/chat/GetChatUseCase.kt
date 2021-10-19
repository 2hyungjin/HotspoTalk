package com.example.domain.usecase.chat

import com.example.domain.base.ParamsUseCase
import com.example.domain.repository.ChatRepository
import javax.inject.Inject

class GetChatUseCase @Inject constructor(
    private val chatRepository: ChatRepository
) : ParamsUseCase<GetChatUseCase.Params, String>() {

    override suspend fun buildUseCase(params: Params): String {
        return chatRepository.getChat(params.roomId)
    }

    data class Params(
        val roomId: Int
    )
}