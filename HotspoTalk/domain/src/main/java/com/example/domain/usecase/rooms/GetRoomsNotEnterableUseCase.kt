package com.example.domain.usecase.rooms

import com.example.domain.base.BaseUseCse
import com.example.domain.base.ParamsUseCase
import com.example.domain.entity.response.RoomInfo
import com.example.domain.repository.RoomsRepository
import javax.inject.Inject

class GetRoomsNotEnterableUseCase @Inject constructor(
    private val roomsRepository: RoomsRepository
) : BaseUseCse<List<RoomInfo>>() {

    override suspend fun buildUseCase(): List<RoomInfo> {
        return roomsRepository.getRooms()
    }

}