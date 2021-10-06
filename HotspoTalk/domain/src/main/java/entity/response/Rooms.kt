package entity.response

data class Rooms(
    val roomID: Int,
    val roomName: String,
    val roomPW: String?,
    val memberLimit: Int,
    val areaType: Int,
    val areaDetail: Int
)
