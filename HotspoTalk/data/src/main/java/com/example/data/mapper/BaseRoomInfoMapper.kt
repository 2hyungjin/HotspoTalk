package com.example.data.mapper

import com.example.data.entity.response.RoomInfo
import com.example.data.entity.RoomInfoData

fun List<RoomInfo>.toData(): List<RoomInfoData> =
    this.map {
        it.toData()
    }

fun List<RoomInfoData>.toEntity(): List<RoomInfo> =
    this.map {
        it.toEntity()
    }


