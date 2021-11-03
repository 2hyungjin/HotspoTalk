package com.example.hotspotalk.data.entity

import com.example.hotspotalk.data.entity.response.MessageResponse

data class Message(
    val content: String,
    val roomID: Int,
    val memberID: String,
    val timestamp: String
)

enum class MessageType {
    MINE, YOURS, COMMAND
}
