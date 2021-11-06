package com.example.hotspotalk.data.entity.response

data class RoomInfo(
    val roomID: Int,
    val roomName: String,
    val roomPW: String?,
    val existPW: Boolean?,
    val memberLimit: Int,
    val areaType: Int,
    val areaDetail: Int
)
