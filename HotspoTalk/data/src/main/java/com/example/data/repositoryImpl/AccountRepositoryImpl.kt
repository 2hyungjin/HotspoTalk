package com.example.data.repositoryImpl

import com.example.data.datasource.AccountDataSource
import com.example.data.entity.repuest.Ban
import com.example.data.entity.repuest.Login
import com.example.data.repository.AccountRepository
import javax.inject.Inject

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