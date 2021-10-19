package com.example.data.datasource

import com.example.data.base.BaseDataSource
import com.example.data.service.AccountService
import com.example.domain.entity.repuest.Ban
import com.example.domain.entity.repuest.Login
import javax.inject.Inject

class AccountDataSource @Inject constructor(
    override val service: AccountService
) : BaseDataSource<AccountService>() {

    suspend fun postLogin(login: Login): String {
        return getResponse(service.postLogin(login))
    }

    suspend fun postSignUp(signUp: Login): String {
        return getResponse(service.postSignUp(signUp))
    }

    suspend fun getId(id: String): String {
        return getResponse(service.getId(id))
    }

    suspend fun deleteBan(ban: Ban): String {
        return getResponse(service.deleteBan(ban))
    }
}