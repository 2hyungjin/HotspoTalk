package com.example.hotspotalk.data.entity.repuest

data class MessageRequest(
    val content:String,
    val memberId:String,
    val roomId:String,
    val timeStamp:String
)
