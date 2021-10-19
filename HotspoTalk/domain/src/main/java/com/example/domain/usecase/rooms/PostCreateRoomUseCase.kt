package com.example.domain.usecase.rooms

import com.example.domain.base.ParamsUseCase
import com.example.domain.entity.repuest.CreateRoom
import com.example.domain.repository.RoomsRepository
import javax.inject.Inject

class PostCreateRoomUseCase @Inject constructor(
    private val roomsRepository: RoomsRepository
) : ParamsUseCase<PostCreateRoomUseCase.Params, String>() {

    data class Params(
        val createRoom: CreateRoom
    )

    override suspend fun buildUseCase(params: Params): String {
        return roomsRepository.postCreateRoom(params.createRoom)
    }
}