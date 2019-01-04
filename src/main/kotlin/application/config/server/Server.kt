package application.config.server

import application.config.koin.KoinModuleConfig
import application.config.persistence.DatabaseFactory
import application.config.properties.PropertiesUtil
import application.service.UserService
import com.fasterxml.jackson.annotation.JsonInclude
import io.ktor.application.Application
import io.ktor.application.install
import io.ktor.features.CallLogging
import io.ktor.features.ContentNegotiation
import io.ktor.features.DefaultHeaders
import io.ktor.jackson.jackson
import io.ktor.routing.Routing
import io.ktor.server.engine.commandLineEnvironment
import io.ktor.server.engine.embeddedServer
import io.ktor.server.netty.Netty
import io.ktor.server.netty.NettyApplicationEngine
import org.koin.ktor.ext.getProperty
import org.koin.ktor.ext.inject
import org.koin.standalone.StandAloneContext.startKoin

fun startServer(args: Array<String> = arrayOf()): NettyApplicationEngine {
    return embeddedServer(Netty, commandLineEnvironment(args)).start()
}

fun Application.module() {
    
    DatabaseFactory.init(createSchema = PropertiesUtil.getProperty("create-schema").toBoolean())
    startKoin(
        listOf(KoinModuleConfig.applicationModule)
    )

    install(DefaultHeaders)
    install(CallLogging)
    install(ContentNegotiation) {
        jackson {
            setSerializationInclusion(JsonInclude.Include.NON_NULL)
        }
    }
    install(Routing) {
        val userService: UserService by inject()
        setup(userService)
    }

    ServerEvents.register(this)

}