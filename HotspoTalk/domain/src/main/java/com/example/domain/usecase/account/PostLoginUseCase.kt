package com.example.domain.usecase.account

import com.example.domain.base.ParamsUseCase
import com.example.domain.entity.repuest.Login
import com.example.domain.repository.AccountRepository

class PostLoginUseCase(
    private val accountRepository: AccountRepository
) : ParamsUseCase<PostLoginUseCase.Params, String>() {

    override suspend fun buildUseCase(params: Params): String {
        return accountRepository.postLogin(params.login)
    }

    data class Params(
        val login: Login
    )
}