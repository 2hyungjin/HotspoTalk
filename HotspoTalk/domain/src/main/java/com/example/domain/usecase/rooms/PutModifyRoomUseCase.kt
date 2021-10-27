package com.example.domain.usecase.rooms

import com.example.domain.base.ParamsUseCase
import com.example.domain.entity.repuest.ModifyRoom
import com.example.domain.entity.response.Msg
import com.example.domain.repository.RoomsRepository
import javax.inject.Inject

class PutModifyRoomUseCase @Inject constructor(
    private val roomsRepository: RoomsRepository
) : ParamsUseCase<PutModifyRoomUseCase.Params, Msg>() {

    data class Params(
        val roomId: Int,
        val modifyRoom: ModifyRoom
    )

    override suspend fun buildUseCase(params: Params): Msg {
        return roomsRepository.putModifyRoom(params.roomId, params.modifyRoom)
    }
}