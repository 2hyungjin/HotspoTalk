package com.example.hotspotalk.data.entity

import com.example.hotspotalk.data.entity.response.MessageResponse

data class Message(
    val nickname: String,
    var content: String? = null,
    val roomID: Int,
    var timestamp: String = "",
    var messageType: MessageType = MessageType.YOURS,
    var messageId: Int? = null
)

enum class MessageType {
    MINE, YOURS, COMMAND
}
