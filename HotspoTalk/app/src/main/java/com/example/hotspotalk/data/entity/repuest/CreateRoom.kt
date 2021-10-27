package com.example.hotspotalk.data.entity.repuest

data class CreateRoom(
    val name: String?,
    val password: String?,
    val memberLimit: Int?,
    val latitude: Double?,
    val longitude: Double?,
    val areaType: Int?, // 0일때 반경 1일때 주소
    val areaDetail: Int? // 미터
)
