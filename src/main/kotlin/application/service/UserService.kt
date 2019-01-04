package application.service

import application.domain.UserDTO
import application.dto.UserFormDTO

interface UserService {

    fun create(userFormDTO: UserFormDTO): UserDTO

}