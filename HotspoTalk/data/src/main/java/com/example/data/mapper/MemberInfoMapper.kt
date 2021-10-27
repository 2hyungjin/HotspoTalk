package com.example.data.mapper

import com.example.data.entity.response.MemberInfo
import com.example.data.entity.MemberInfoData

fun List<MemberInfo>.toData(): List<MemberInfoData> =
    this as List<MemberInfoData>

fun List<MemberInfoData>.toEntity(): List<MemberInfo> =
    this as List<MemberInfo>