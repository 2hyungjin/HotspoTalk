package com.example.data.repository

import com.example.data.entity.repuest.Ban
import com.example.data.entity.repuest.Login

interface AccountRepository {
    suspend fun postLogin(login: Login): String

    suspend fun postSignUp(signUp: Login): String

    suspend fun getId(id: String): String

    suspend fun deleteBan(ban: Ban): String
}