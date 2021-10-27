package com.example.data.repository

import com.example.data.datasource.AccountDataSource
import com.example.domain.entity.repuest.Ban
import com.example.domain.entity.repuest.Login
import com.example.domain.entity.response.Msg
import com.example.domain.entity.response.Token
import com.example.domain.repository.AccountRepository
import javax.inject.Inject
import kotlin.math.log

class AccountRepositoryImpl @Inject constructor(
    private val dataSource: AccountDataSource
) : AccountRepository {

    override suspend fun postLogin(login: Login): Token {
        return dataSource.postLogin(login)
    }

    override suspend fun postSignUp(signUp: Login): Token {
        return dataSource.postSignUp(signUp)
    }

    override suspend fun getId(id: String): Msg {
        return dataSource.getId(id)
    }

    override suspend fun deleteBan(ban: Ban): Msg {
        return dataSource.deleteBan(ban)
    }
}