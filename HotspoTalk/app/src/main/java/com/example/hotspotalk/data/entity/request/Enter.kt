/* 방 입장 시 사용 */
package com.example.hotspotalk.data.entity.request

data class Enter(
    val roomID: Int,
    val nickname: String?,
    val password: String?
)
