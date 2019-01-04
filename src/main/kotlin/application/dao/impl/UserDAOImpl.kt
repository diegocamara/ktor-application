package application.dao.impl

import application.dao.UserDAO
import application.domain.User
import application.domain.UserDTO
import application.dto.UserFormDTO
import org.jetbrains.exposed.sql.transactions.transaction

class UserDAOImpl : UserDAO {

    override fun create(userFormDTO: UserFormDTO): UserDTO {
        return User.new {
            this.name = userFormDTO.username.toString()
            this.email = userFormDTO.email.toString()
            this.password = userFormDTO.password.toString()
        }.let {
            toUserDTO(it)
        }
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