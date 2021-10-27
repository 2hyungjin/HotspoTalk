package com.example.domain.usecase.rooms

import com.example.domain.base.ParamsUseCase
import com.example.domain.entity.repuest.Enter
import com.example.domain.entity.response.Msg
import com.example.domain.repository.RoomsRepository
import javax.inject.Inject

class GetEnterRoomUseCase @Inject constructor(
    private val roomsRepository: RoomsRepository
) : ParamsUseCase<GetEnterRoomUseCase.Params, Msg>() {

    data class Params(
        val roomId: Int,
        val enter: Enter
    )

    override suspend fun buildUseCase(params: Params): Msg {
        return roomsRepository.getEnterRoom(params.roomId, params.enter)
    }
}