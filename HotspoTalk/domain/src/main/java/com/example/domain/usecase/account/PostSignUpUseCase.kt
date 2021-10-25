package com.example.domain.usecase.account

import com.example.domain.base.ParamsUseCase
import com.example.domain.entity.repuest.Login
import com.example.domain.entity.response.Token
import com.example.domain.repository.AccountRepository
import java.util.function.LongToIntFunction
import javax.inject.Inject

class PostSignUpUseCase @Inject constructor(
    private val accountRepository: AccountRepository
) : ParamsUseCase<PostSignUpUseCase.Params, Token>() {

    override suspend fun buildUseCase(params: Params): Token {
        return accountRepository.postSignUp(params.signUp)
    }

    data class Params(
        val signUp: Login
    )
}