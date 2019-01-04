package application.config.server

import application.config.server.routes.user
import application.service.UserService
import io.ktor.routing.Routing

fun Routing.setup(userService: UserService){
    user(userService)
}