package com.example.hotspotalk.data.repositoryImpl

import com.example.hotspotalk.data.entity.Message
import com.example.hotspotalk.data.repository.AccountRepository
import com.example.hotspotalk.data.entity.repuest.Ban
import com.example.hotspotalk.data.entity.repuest.Login
import com.example.hotspotalk.data.entity.repuest.SignUp
import com.example.hotspotalk.data.entity.response.Token
import com.example.hotspotalk.data.service.AccountService
import retrofit2.Response
import javax.inject.Inject

class AccountRepositoryImpl @Inject constructor(
    private val service: AccountService
) : AccountRepository {

    override suspend fun postLogin(login: Login): Response<Token> {
        return service.postLogin(login)
    }

    override suspend fun postSignUp(signUp: SignUp): Response<Token> {
        return service.postSignUp(signUp)
    }

    override suspend fun getId(id: String): Response<Message> {
        return service.getId(id)
    }

    override suspend fun deleteBan(ban: Ban): Response<Message> {
        return service.deleteBan(ban)
    }
}