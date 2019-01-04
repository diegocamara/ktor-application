package application

import application.config.server.module
import application.domain.UserDTO
import application.dto.UserFormDTO
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import io.ktor.application.Application
import io.ktor.http.HttpMethod
import io.ktor.http.HttpStatusCode
import io.ktor.server.testing.handleRequest
import io.ktor.server.testing.setBody
import io.ktor.server.testing.withTestApplication
import junit.framework.TestCase

class IntegrationTest : TestCase() {

    fun `testar se o usuário foi criado`() = withTestApplication(Application::module) {

        val userFormDTO = UserFormDTO(username = "user1", password = "123")

        var requestBody = jacksonObjectMapper().writeValueAsString(userFormDTO)

        with(handleRequest(HttpMethod.Post, "/user") {
            setBody(requestBody)
        }) {
            assertEquals(HttpStatusCode.Created, response.status())
            assertNotNull(jacksonObjectMapper().readValue(response.content, UserDTO::class.java).id)
        }
    }

}