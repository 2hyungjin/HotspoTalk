package com.example.domain.usecase.account

import com.example.domain.base.ParamsUseCase
import com.example.domain.repository.AccountRepository
import javax.inject.Inject

class GetIdUseCase @Inject constructor(
    private val accountRepository: AccountRepository
) : ParamsUseCase<GetIdUseCase.Params, String>() {

    override suspend fun buildUseCase(params: Params): String {
        return accountRepository.getId(params.id)
    }

    data class Params(
        val id: String
    )
}