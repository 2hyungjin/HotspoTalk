package com.example.domain.usecase.account

import com.example.domain.base.ParamsUseCase
import com.example.domain.entity.repuest.Ban
import com.example.domain.repository.AccountRepository
import javax.inject.Inject

class DeleteBanUseCase @Inject constructor(
    private val accountRepository: AccountRepository
) : ParamsUseCase<DeleteBanUseCase.Params, String>() {

    override suspend fun buildUseCase(params: Params): String {
        return accountRepository.deleteBan(params.ban)
    }

    data class Params(
        val ban: Ban
    )
}