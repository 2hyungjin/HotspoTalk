package com.example.hotspotalk.data.entity.response

data class MessageResponse(
    val content: String,
    val roomID: Int,
    val memberID: String,
    val timestamp: String
)
