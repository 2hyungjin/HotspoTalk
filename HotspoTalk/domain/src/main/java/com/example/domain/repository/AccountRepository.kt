package com.example.domain.repository

import com.example.domain.entity.repuest.Ban
import com.example.domain.entity.repuest.Login

interface AccountRepository {
    suspend fun postLogin(login: Login): String

    suspend fun postSignUp(signUp: Login): String

    suspend fun getId(id: String): String

    suspend fun deleteBan(ban: Ban): String
}