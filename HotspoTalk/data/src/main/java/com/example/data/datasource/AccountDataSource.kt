package com.example.data.datasource

import com.example.data.base.BaseDataSource
import com.example.data.service.AccountService
import com.example.domain.entity.repuest.Ban
import com.example.domain.entity.repuest.Login
import javax.inject.Inject

class AccountDataSource @Inject constructor(
    override val service: AccountService
) : BaseDataSource<AccountService>() {

    suspend fun postLogin(login: Login): Token {
        return getResponse(service.postLogin(login)).toEntity()
    }

    suspend fun postSignUp(signUp: Login): Token {
        return getResponse(service.postSignUp(signUp)).toEntity()
    }

    suspend fun getId(id: String): Msg {
        return getResponse(service.getId(id)).toEntity()
    }

    suspend fun deleteBan(ban: Ban): Msg {
        return getResponse(service.deleteBan(ban)).toEntity()
    }
}