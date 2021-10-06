package entity.repuest

data class LogInRequest(
    val id: String,
    val password: String,
    val devToken: String
)
