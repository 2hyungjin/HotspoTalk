package com.example.data.mapper

import com.example.domain.entity.response.RoomInfo
import com.example.data.entity.RoomInfoData

fun List<RoomInfo>.toData(): List<RoomInfoData> =
    this as List<RoomInfoData>

fun List<RoomInfoData>.toEntity(): List<RoomInfo> =
    this as List<RoomInfo>


fun RoomInfo.toData() =
    RoomInfoData(
        this.roomID,
        this.roomName,
        this.roomPW,
        this.memberLimit,
        this.areaType,
        this.areaDetail
    )

fun RoomInfoData.toEntity() =
    RoomInfo(
        this.roomID,
        this.roomName,
        this.roomPW,
        this.memberLimit,
        this.areaType,
        this.areaDetail
    )