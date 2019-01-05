package application.dao.impl

import application.dao.UserDAO
import application.domain.User
import application.domain.UserDTO
import application.domain.Users
import application.dto.UserFormDTO
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.transactions.transaction
import java.util.*

class UserDAOImpl : UserDAO {

    override fun create(userFormDTO: UserFormDTO): UserDTO {
        return User.new {
            this.name = userFormDTO.username
            this.email = userFormDTO.email
            this.password = userFormDTO.password
        }.let {
            toUserDTO(it)
        }
    }

    override fun findById(id: String): UserDTO? {
        return Users.slice(Users.id, Users.name, Users.email).select { Users.id.eq(UUID.fromString(id)) }.map {
            UserDTO(id = it[Users.id].toString(), name = it[Users.name], email = it[Users.email])
        }.firstOrNull()
    }

    private fun toUserDTO(user: User?): UserDTO {
        return UserDTO(
            id = user?.id.toString(),
            name = user?.name,
            email = user?.email,
            password = user?.password
        )
    }

}