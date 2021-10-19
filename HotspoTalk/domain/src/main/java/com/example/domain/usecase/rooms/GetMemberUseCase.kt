package com.example.domain.usecase.rooms

import com.example.domain.base.ParamsUseCase
import com.example.domain.entity.response.MemberInfo
import com.example.domain.repository.RoomsRepository
import javax.inject.Inject

class GetMemberUseCase @Inject constructor(
    private val roomsRepository: RoomsRepository
) : ParamsUseCase<GetMemberUseCase.Params, List<MemberInfo>>() {

    data class Params(
        val roomId: Int
    )

    override suspend fun buildUseCase(params: Params): List<MemberInfo> {
        return roomsRepository.getMember(params.roomId)
    }
}