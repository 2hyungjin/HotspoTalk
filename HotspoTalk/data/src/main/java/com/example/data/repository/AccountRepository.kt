package com.example.data.repository

import com.example.data.entity.repuest.Ban
import com.example.data.entity.repuest.Login
import com.example.data.entity.response.Token
import com.example.domain.entity.response.Msg

interface AccountRepository {
    suspend fun postLogin(login: Login): Token

    suspend fun postSignUp(signUp: Login): Token

    suspend fun getId(id: String): Msg

    suspend fun deleteBan(ban: Ban): Msg
}