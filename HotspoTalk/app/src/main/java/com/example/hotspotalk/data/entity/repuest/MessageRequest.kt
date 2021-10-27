package com.example.hotspotalk.data.entity.repuest

data class MessageRequest(
    val content:String,
    val account:String,
    val roomId:String,
    val timeStamp:String
)
