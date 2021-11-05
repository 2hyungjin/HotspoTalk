package com.example.hotspotalk.data.entity

import com.example.hotspotalk.data.entity.response.MessageResponse

data class Message(
    val nickname: String,
    var content: String? = null,
    val roomID: Int,
    val memberID: String,
    val timestamp: String,
    var messageType: MessageType = MessageType.YOURS,
    val messageId: Int
)

enum class MessageType {
    MINE, YOURS, COMMAND
}
