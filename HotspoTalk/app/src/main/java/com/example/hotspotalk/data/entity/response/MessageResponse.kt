package com.example.hotspotalk.data.entity.response

data class MessageResponse(
    val content: String,
    val memberId: String,
    val roomId: String,
    val messageId: String,
    val timeStamp: String,
)
