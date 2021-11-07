package com.example.hotspotalk.data.entity.response

import android.util.Log
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
    val timestamp: String,
    val messageId: Int,
    val isMe: Boolean
) {
    fun toMessage(): Message {
        Log.d("Message", "toMessage: $timestamp")
        val dateFormat = SimpleDateFormat("yyyy-MM-dd'T'hh:mm:ss.SSS'Z'", Locale.KOREA)
        val time = dateFormat.parse(timestamp)?.time ?: 0
        return Message(
            content = content,
            nickname = nickname,
            roomID = roomID,
            messageId = messageId,
            timestamp = SimpleDateFormat("hh:mm", Locale.KOREA).format(Date(time)),
            messageType = if (type == "msg" && isMe) MessageType.MINE else if (type == "msg" && !isMe) MessageType.YOURS else MessageType.COMMAND
        )
    }


}
