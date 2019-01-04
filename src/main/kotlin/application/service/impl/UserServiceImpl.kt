package application.service.impl

import application.dao.UserDAO
import application.domain.UserDTO
import application.dto.UserFormDTO
import application.service.UserService
import org.jetbrains.exposed.sql.transactions.transaction

class UserServiceImpl(private val userDAO: UserDAO) : UserService {

    override fun create(userFormDTO: UserFormDTO): UserDTO {
        return transaction { userDAO.create(userFormDTO) }
    }

}