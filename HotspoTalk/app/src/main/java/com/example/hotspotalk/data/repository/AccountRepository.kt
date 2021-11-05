package com.example.hotspotalk.data.repository

import com.example.hotspotalk.data.entity.Message
import com.example.hotspotalk.data.entity.request.Ban
import com.example.hotspotalk.data.entity.request.Login
import com.example.hotspotalk.data.entity.request.SignUp
import com.example.hotspotalk.data.entity.response.Token
import retrofit2.Response

interface AccountRepository {
    suspend fun postLogin(login: Login): Response<Token>

    suspend fun postSignUp(signUp: SignUp): Response<Token>

    suspend fun postAutoLogin(): Response<Any?>

    suspend fun getId(id: String): Response<Message>

    suspend fun deleteBan(ban: Ban): Response<Message>
}