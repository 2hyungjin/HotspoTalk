package com.example.domain.usecase.rooms

import com.example.domain.base.ParamsUseCase
import com.example.domain.entity.repuest.ModifyNickname
import com.example.domain.repository.RoomsRepository
import javax.inject.Inject

class PutModifyNicknameUseCase @Inject constructor(
    private val roomsRepository: RoomsRepository
) : ParamsUseCase<PutModifyNicknameUseCase.Params, String>() {

    data class Params(
        val roomId: Int,
        val modifyNickname: ModifyNickname
    )

    override suspend fun buildUseCase(params: Params): String {
        return roomsRepository.putModifyNickname(params.roomId, params.modifyNickname)
    }
}