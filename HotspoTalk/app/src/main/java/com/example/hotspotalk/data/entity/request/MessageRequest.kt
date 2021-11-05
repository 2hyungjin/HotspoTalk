package com.example.hotspotalk.data.entity.request

data class MessageRequest(
    val type: String,
    val roomId: Int,
    val content: String,
    val token: String
)
