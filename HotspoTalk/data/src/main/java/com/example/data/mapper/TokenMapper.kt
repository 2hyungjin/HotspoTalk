package com.example.data.mapper

import com.example.data.entity.TokenData
import com.example.data.entity.response.Token

fun Token.toData() =
    TokenData(this.token)

fun TokenData.toEntity() =
    Token(this.token)