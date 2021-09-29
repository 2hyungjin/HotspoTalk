package com.example.hotspotalk.view.model

data class ChattingRoom(
    val roomName: String,
    val user: Int,
    val nickname: String,
    val isNotify: Boolean,
    val isSecret: Boolean
)
