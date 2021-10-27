package com.example.data.mapper

import com.example.data.entity.MemberInfoData
import com.example.data.entity.response.MemberInfo

fun List<MemberInfo>.toData(): List<MemberInfoData> =
    this.map {
        it.toData()
    }

fun List<MemberInfoData>.toEntity(): List<MemberInfo> =
    this.map {
        it.toEntity()
    }