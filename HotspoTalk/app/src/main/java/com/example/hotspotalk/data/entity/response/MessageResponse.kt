package com.example.hotspotalk.data.entity.response

import com.example.hotspotalk.data.entity.Message
import com.example.hotspotalk.data.entity.MessageType

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
        timestamp = timestamp,
        messageType = if (type == "msg" && isMe) MessageType.MINE else if (type == "msg" && !isMe) MessageType.YOURS else MessageType.COMMAND
    )


}
