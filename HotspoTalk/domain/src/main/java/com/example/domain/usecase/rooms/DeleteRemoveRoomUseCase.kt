package com.example.domain.usecase.rooms

import com.example.domain.base.ParamsUseCase
import com.example.domain.repository.RoomsRepository
import javax.inject.Inject

class DeleteRemoveRoomUseCase @Inject constructor(
    private val roomsRepository: RoomsRepository
) : ParamsUseCase<DeleteRemoveRoomUseCase.Params, String>() {

    data class Params(
        val roomId: Int
    )

    override suspend fun buildUseCase(params: Params): String {
        return roomsRepository.deleteRemoveRoom(params.roomId)
    }
}