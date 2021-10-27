package com.example.hotspotalk.data.entity

import com.example.hotspotalk.data.entity.response.MessageResponse

data class Message(val message: MessageResponse, val messageType: MessageType)
enum class MessageType{
    MINE,YOURS,COMMAND
}
