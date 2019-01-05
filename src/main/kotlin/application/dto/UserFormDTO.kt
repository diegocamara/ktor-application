package application.dto

data class UserFormDTO(
    val username: String,
    val password: String,
    val email: String
) : AbstractDTO()