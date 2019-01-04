package application.dao

import application.domain.UserDTO
import application.dto.UserFormDTO

interface UserDAO {

   fun create(userFormDTO: UserFormDTO): UserDTO

}