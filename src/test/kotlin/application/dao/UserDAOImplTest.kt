package application.dao

import application.config.persistence.DatabaseFactory
import application.dao.impl.UserDAOImpl
import application.domain.UserDTO
import application.dto.UserFormDTO
import junit.framework.TestCase
import org.jetbrains.exposed.sql.transactions.transaction

class UserDAOImplTest : TestCase() {

    private lateinit var userDAO: UserDAO

    override fun setUp() {
        DatabaseFactory.init(createSchema = true)
        userDAO = UserDAOImpl()
    }

    override fun tearDown() {
        DatabaseFactory.drop()
    }

    fun `testar se um usuário é criado no db`() {

        val userFormDTO = UserFormDTO(username = "user", email = "user@users.com", password = "123")

        val userDTO = transaction { userDAO.create(userFormDTO) }

        assertNotNull(userDTO.id)

    }

    fun `testar se um usuário foi encontrado no db`(){

        val userFormDTO = UserFormDTO(username = "user1", email = "user1@users.com", password = "123")

        var resultUser: UserDTO? = null

        transaction {

            val userDTO = userDAO.create(userFormDTO)

            resultUser = userDAO.findById(userDTO.id.toString())

        }

        assertNotNull(resultUser)

    }

    fun `testar se os dados passados na implementação do método create são os mesmos retornados pelo db`() {

        val userFormDTO = UserFormDTO(username = "user1", email = "user1@users.com", password = "123")

        var resultUser: UserDTO? = null

        transaction {

            val userDTO = userDAO.create(userFormDTO)

            resultUser = userDAO.findById(userDTO.id.toString())

        }

        assertEquals(userFormDTO.username, resultUser?.name)
        assertEquals(userFormDTO.email, resultUser?.email)

    }

}