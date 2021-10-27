package com.example.data.repositoryImpl

import com.example.data.datasource.AccountDataSource
import com.example.data.entity.repuest.Ban
import com.example.data.entity.repuest.Login
import com.example.data.entity.response.Token
import com.example.data.repository.AccountRepository
import com.example.domain.entity.response.Msg
import javax.inject.Inject

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