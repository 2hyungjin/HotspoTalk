package com.example.domain.usecase.rooms

import com.example.domain.base.ParamsUseCase
import com.example.domain.entity.repuest.AccountId
import com.example.domain.entity.repuest.CreateRoom
import com.example.domain.entity.response.Msg
import com.example.domain.repository.RoomsRepository
import javax.inject.Inject

class PutInheritRoomUseCase @Inject constructor(
    private val roomsRepository: RoomsRepository
) : ParamsUseCase<PutInheritRoomUseCase.Params, Msg>() {

    data class Params(
        val accountId: AccountId
    )

    override suspend fun buildUseCase(params: Params): Msg {
        return roomsRepository.putInheritRoom(params.accountId)
    }

}