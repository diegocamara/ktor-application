package application.config.server.routes

import application.domain.UserDTO
import application.dto.UserFormDTO
import application.service.UserService
import io.ktor.application.call
import io.ktor.http.HttpStatusCode
import io.ktor.request.receive
import io.ktor.response.respond
import io.ktor.routing.Route
import io.ktor.routing.post
import io.ktor.routing.route

fun Route.user(userService: UserService) {
    route("user") {
        post {
            var userFormDTO = call.receive<UserFormDTO>()
            val userDTO: UserDTO = userService.create(userFormDTO)
            call.respond(HttpStatusCode.Created, userDTO)
        }
    }
}