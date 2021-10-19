package com.example.domain.usecase.rooms

import com.example.domain.base.ParamsUseCase
import com.example.domain.entity.response.RoomInfo
import com.example.domain.repository.RoomsRepository
import javax.inject.Inject

class GetRoomsEnterableUseCase @Inject constructor(
    private val roomsRepository: RoomsRepository
) : ParamsUseCase<GetRoomsEnterableUseCase.Params, List<RoomInfo>>() {

    data class Params(
        val latitude: Int,
        val longitude: Int
    )

    override suspend fun buildUseCase(params: Params): List<RoomInfo> {
        return roomsRepository.getRooms(params.latitude, params.longitude)
    }
}