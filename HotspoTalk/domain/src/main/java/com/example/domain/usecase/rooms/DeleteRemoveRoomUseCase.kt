package com.example.domain.usecase.rooms

import com.example.domain.base.ParamsUseCase
import com.example.domain.entity.response.Msg
import com.example.domain.repository.RoomsRepository
import javax.inject.Inject

class DeleteRemoveRoomUseCase @Inject constructor(
    private val roomsRepository: RoomsRepository
) : ParamsUseCase<DeleteRemoveRoomUseCase.Params, Msg>() {

    data class Params(
        val roomId: Int
    )

    override suspend fun buildUseCase(params: Params): Msg {
        return roomsRepository.deleteRemoveRoom(params.roomId)
    }
}