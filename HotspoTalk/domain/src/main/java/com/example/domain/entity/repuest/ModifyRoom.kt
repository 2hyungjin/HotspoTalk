package com.example.domain.entity.repuest

data class ModifyRoom(
    val name: String,
    val password: String,
    val memberLimit: Int,
    val areaType: Int,
    val areaDetail: Int
)
