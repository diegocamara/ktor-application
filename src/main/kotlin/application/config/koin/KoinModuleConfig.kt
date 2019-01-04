package application.config.koin

import application.dao.UserDAO
import application.dao.impl.UserDAOImpl
import application.service.UserService
import application.service.impl.UserServiceImpl
import org.koin.dsl.module.module

object KoinModuleConfig {
    val applicationModule = module {
        single { UserDAOImpl() as UserDAO }
        single { UserServiceImpl(get()) as UserService }
    }
}