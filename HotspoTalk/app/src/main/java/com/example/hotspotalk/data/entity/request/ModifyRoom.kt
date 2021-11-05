package com.example.hotspotalk.data.entity.request

data class ModifyRoom(
    val name: String,
    val password: String,
    val memberLimit: Int,
    val areaType: Int,
    val areaDetail: Int
)
