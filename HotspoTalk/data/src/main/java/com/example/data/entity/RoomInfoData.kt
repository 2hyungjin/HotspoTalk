package com.example.data.entity

data class RoomInfoData(
    val roomID: Int,
    val roomName: String,
    val roomPW: String?,
    val memberLimit: Int,
    val areaType: Int,
    val areaDetail: Int
)