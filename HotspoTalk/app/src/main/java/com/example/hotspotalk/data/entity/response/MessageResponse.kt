package com.example.hotspotalk.data.entity.response

import com.example.hotspotalk.data.entity.Message
import com.example.hotspotalk.data.entity.MessageType

data class MessageResponse(
    val type: String,
    val content: String,
    val roomID: Int,
    val nickname: String,
    val memberID: String,
    val timestamp: String,
    val messageId: Int
) {
    fun toMessage(): Message {
        val message = Message(
            nickname = nickname,
            content = content,
            roomID = roomID,
            memberID = memberID,
            timestamp = timestamp,
            messageId = messageId
        )

        when (type) {
            "msg" -> {
                message.content = content
                message.messageType = MessageType.YOURS
            }
            "in" -> {
                message.content = "${memberID}님이 입장하였습니다."
                message.messageType = MessageType.COMMAND
            }
            "out" -> {
                message.content = "${memberID}님이 퇴장하였습니다."
                message.messageType = MessageType.COMMAND
            }
        }
        return message
    }
}
