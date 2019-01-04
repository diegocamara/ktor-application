package application.config.properties

import com.typesafe.config.ConfigFactory
import io.ktor.config.HoconApplicationConfig

object PropertiesUtil {

    private val hoconApplicationConfig: HoconApplicationConfig = HoconApplicationConfig(ConfigFactory.load())

    fun getProperty(path: String): String {
        return hoconApplicationConfig.property(path).getString()
    }

}