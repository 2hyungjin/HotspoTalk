package com.example.hotspotalk.data.entity

data class Message(
    val nickname: String,
    var content: String,
    val roomID: Int,
    var timestamp: String?,
    var messageType: MessageType,
    var messageId: Int? = null
)

enum class MessageType {
    MINE, YOURS, COMMAND, BREAK
}
