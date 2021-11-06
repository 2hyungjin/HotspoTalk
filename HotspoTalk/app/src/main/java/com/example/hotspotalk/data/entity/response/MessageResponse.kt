package com.example.hotspotalk.data.entity.response

import com.example.hotspotalk.data.entity.Message
import com.example.hotspotalk.data.entity.MessageType

data class MessageResponse(
    val type: String,
    val content: String?,
    val roomID: Int,
    val nickname: String,
    val timestamp: String?,
    val messageId: Int,
    val isMe: Boolean
) {
    fun toMessage(): Message {

        lateinit var message: Message

        when (type) {
            "msg" -> {
                message = Message(
                    content = content!!,
                    nickname = nickname,
                    roomID = roomID,
                    messageId = messageId,
                    timestamp = timestamp,
                    messageType = if (isMe) MessageType.MINE else MessageType.YOURS
                )

            }
            "in", "out" -> {
                message = Message(
                    content = if (type == "in") "${nickname}님이 입장하였습니다." else "${nickname}님이 퇴장하였습니다",
                    nickname = nickname,
                    roomID = roomID,
                    messageId = messageId,
                    timestamp = timestamp,
                    messageType = MessageType.COMMAND

                )
            }

        }
        return message
    }
}
