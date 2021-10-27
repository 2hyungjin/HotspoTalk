package com.example.hotspotalk.data.entity.repuest

data class CreateRoom(
    val name: String?,
    val password: String?,
    val memberLimit: Int?,
    val latitude: Int?,
    val longitude: Int?,
    val areaType: Int?,
    val areaDetail: Int?
)
