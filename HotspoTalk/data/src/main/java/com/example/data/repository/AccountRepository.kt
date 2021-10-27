package com.example.domain.repository

import com.example.domain.entity.repuest.Ban
import com.example.domain.entity.repuest.Login

interface AccountRepository {
    suspend fun postLogin(login: Login): Token

    suspend fun postSignUp(signUp: Login): Token

    suspend fun getId(id: String): Msg

    suspend fun deleteBan(ban: Ban): Msg
}