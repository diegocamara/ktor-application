package application.dto

data class UserFormDTO(
    val username: String? = null,
    val password: String? = null,
    val email: String? = null
) : AbstractDTO()