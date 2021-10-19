package com.example.domain.entity.response

data class RoomInfo(
    val roomID: Int,
    val roomName: String,
    val roomPW: String?,
    val memberLimit: Int,
    val areaType: Int,
    val areaDetail: Int
)
