package com.example.data.mapper

import com.example.data.entity.response.MemberInfo
import com.example.data.entity.MemberInfoData

fun MemberInfo.toData(): MemberInfoData =
    MemberInfoData(
        this.accountId,
        this.nickName,
    )


fun MemberInfoData.toEntity(): MemberInfo =
    MemberInfo(
        this.accountId,
        this.nickName,
    )