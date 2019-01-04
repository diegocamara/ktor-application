package application.config.server

import io.ktor.application.Application
import io.ktor.application.ApplicationEvents
import io.ktor.application.ApplicationStopping
import org.koin.standalone.StandAloneContext.stopKoin
import application.config.persistence.DatabaseFactory

object ServerEvents {

    fun register(application: Application) {

        val applicationEvents: ApplicationEvents = application.environment.monitor

        applicationEvents.subscribe(ApplicationStopping) {
            DatabaseFactory.drop()
            stopKoin()
        }

    }

}