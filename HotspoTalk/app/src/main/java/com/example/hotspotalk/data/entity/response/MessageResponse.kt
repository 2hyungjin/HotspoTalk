package com.example.hotspotalk.data.entity.response

import com.example.hotspotalk.data.entity.Message
import com.example.hotspotalk.data.entity.MessageType
import java.text.SimpleDateFormat
import java.util.*

data class MessageResponse(
    val type: String,
    val content: String?,
    val roomID: Int,
    val nickname: String,
    val timestamp: String?,
    val messageId: Int
) {
    fun toMessage(): Message {

        val message = Message(
            nickname = nickname,
            roomID = roomID,
            messageId = messageId
        )

        when (type) {
            "msg" -> {
                message.content = content
                message.messageType = MessageType.YOURS
                if (timestamp != null) {
                    val yyyyMMdd = SimpleDateFormat("yyyy-MM-dd hh:mm:ss.SSS", Locale.KOREA).parse(timestamp)
                    message.timestamp = SimpleDateFormat("hh:mm:ss", Locale.KOREA).format(yyyyMMdd!!)
                }
            }
            "in" -> {
                message.content = "${nickname}님이 입장하였습니다."
                message.messageType = MessageType.COMMAND
            }
            "out" -> {
                message.content = "${nickname}님이 퇴장하였습니다."
                message.messageType = MessageType.COMMAND
            }
        }
        return message
    }
}
