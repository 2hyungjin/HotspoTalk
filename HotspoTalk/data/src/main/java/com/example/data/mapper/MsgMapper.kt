package com.example.data.mapper

import com.example.data.entity.MsgData
import com.example.domain.entity.response.Msg

fun Msg.toData() =
    MsgData(this.msg)

fun MsgData.toEntity() =
    Msg(this.msg)