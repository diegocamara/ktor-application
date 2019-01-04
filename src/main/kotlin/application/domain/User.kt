package application.domain

import com.fasterxml.jackson.annotation.JsonIgnore
import org.jetbrains.exposed.dao.EntityID
import org.jetbrains.exposed.dao.UUIDEntity
import org.jetbrains.exposed.dao.UUIDEntityClass
import org.jetbrains.exposed.dao.UUIDTable
import java.util.*


data class UserDTO(
    var id: String? = null,
    var name: String? = null,
    var email: String? = null,
    @JsonIgnore var password: String? = null
)

class User(id: EntityID<UUID>) : UUIDEntity(id) {
    companion object : UUIDEntityClass<User>(Users)

    var name by Users.name
    var email by Users.email
    var password by Users.password
}

object Users : UUIDTable() {
    val name = Users.varchar("name", 255)
    val email = Users.varchar("email", 255)
    var password = Users.varchar("password", 255)
}