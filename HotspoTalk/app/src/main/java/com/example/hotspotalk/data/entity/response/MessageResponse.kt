package com.example.hotspotalk.data.entity.response

import com.example.hotspotalk.data.entity.Message
import com.example.hotspotalk.data.entity.MessageType
import java.lang.String.format
import java.text.SimpleDateFormat
import java.time.Instant
import java.util.*

data class MessageResponse(
    val type: String,
    val content: String,
    val roomID: Int,
    val nickname: String,
    val timestamp: String?,
    val messageId: Int,
    val isMe: Boolean
) {
    fun toMessage(): Message = Message(
        content = content,
        nickname = nickname,
        roomID = roomID,
        messageId = messageId,
        timestamp = SimpleDateFormat("hh:mm", Locale.KOREA).format(Date(Instant.parse(timestamp).epochSecond)),
        messageType = if (type == "msg" && isMe) MessageType.MINE else if (type == "msg" && !isMe) MessageType.YOURS else MessageType.COMMAND
    )


}
