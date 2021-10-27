package com.example.data.mapper

import com.example.data.entity.RoomInfoData
import com.example.data.entity.response.RoomInfo

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