package entity.repuest

data class RoomRequest(
    val name: String?,
    val password: String?,
    val memberLimit: Int?,
    val latitude: Int?,
    val longitude: Int?,
    val areaType: Int?,
    val areaDetail: Int?
)
