package com.example.hotspotalk.data.entity.response

import com.example.hotspotalk.data.entity.Message

data class MessageResponse(
    val content: String,
    val roomID: Int,
    val memberID: String,
    val timestamp: String
) {
    fun toMessage() = Message(content, roomID, memberID, timestamp)
}
