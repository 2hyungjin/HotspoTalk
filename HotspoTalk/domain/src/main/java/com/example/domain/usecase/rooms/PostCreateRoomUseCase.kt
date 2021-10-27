package com.example.domain.usecase.rooms

import com.example.domain.base.ParamsUseCase
import com.example.domain.entity.repuest.CreateRoom
import com.example.domain.entity.response.Msg
import com.example.domain.repository.RoomsRepository
import javax.inject.Inject

class PostCreateRoomUseCase @Inject constructor(
    private val roomsRepository: RoomsRepository
) : ParamsUseCase<PostCreateRoomUseCase.Params, Msg>() {

    data class Params(
        val createRoom: CreateRoom
    )

    override suspend fun buildUseCase(params: Params): Msg {
        return roomsRepository.postCreateRoom(params.createRoom)
    }
}