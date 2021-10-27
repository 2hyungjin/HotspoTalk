package com.example.hotspotalk.data.repository

import com.example.hotspotalk.data.entity.repuest.Ban
import com.example.hotspotalk.data.entity.repuest.Login
import com.example.hotspotalk.data.entity.response.Token
import com.example.hotspotalk.data.entity.response.Msg
import retrofit2.Response

interface AccountRepository {
    suspend fun postLogin(login: Login): Response<Token>

    suspend fun postSignUp(signUp: Login): Response<Token>

    suspend fun getId(id: String): Response<Msg>

    suspend fun deleteBan(ban: Ban): Response<Msg>
}