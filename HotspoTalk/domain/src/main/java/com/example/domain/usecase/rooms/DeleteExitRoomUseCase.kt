package com.example.domain.usecase.rooms

import com.example.domain.base.ParamsUseCase
import com.example.domain.repository.AccountRepository
import com.example.domain.repository.RoomsRepository
import com.example.domain.usecase.account.PostLoginUseCase
import javax.inject.Inject

class DeleteExitRoomUseCase @Inject constructor(
    private val roomsRepository: RoomsRepository
) : ParamsUseCase<DeleteExitRoomUseCase.Params, String>() {

    override suspend fun buildUseCase(params: Params): String {
        return roomsRepository.deleteExitRoom(params.roomId)
    }

    data class Params(
        val roomId: Int
    )


}