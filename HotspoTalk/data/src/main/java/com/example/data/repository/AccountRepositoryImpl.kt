package com.example.data.repository

import com.example.data.datasource.AccountDataSource
import com.example.domain.entity.repuest.Ban
import com.example.domain.entity.repuest.Login
import com.example.domain.repository.AccountRepository
import javax.inject.Inject
import kotlin.math.log

class AccountRepositoryImpl @Inject constructor(
    private val dataSource: AccountDataSource
) : AccountRepository {

    override suspend fun postLogin(login: Login): String {
        return dataSource.postLogin(login)
    }

    override suspend fun postSignUp(signUp: Login): String {
        return dataSource.postSignUp(signUp)
    }

    override suspend fun getId(id: String): String {
        return dataSource.getId(id)
    }

    override suspend fun deleteBan(ban: Ban): String {
        return dataSource.deleteBan(ban)
    }
}