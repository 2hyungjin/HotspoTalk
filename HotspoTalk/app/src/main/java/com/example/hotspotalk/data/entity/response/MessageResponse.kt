package com.example.hotspotalk.data.entity.response

import com.example.hotspotalk.data.entity.Message
import com.example.hotspotalk.data.entity.MessageType

data class MessageResponse(
    val type: String,
    val msg: String,
    val roomID: Int,
    val memberID: String,
    val timestamp: String,
    val messageId: Int
) {
    fun toMessage(): Message {
        val message = Message(
            roomID = roomID,
            memberID = memberID,
            timestamp = timestamp,
            messageId = messageId
        )

        when (type) {
            "msg" -> {
                message.content = msg
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
