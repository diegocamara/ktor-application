package application.dto

import application.config.mapper.objectMapper

open class AbstractDTO {

    fun toJson(): String {
        return objectMapper().writeValueAsString(this)
    }

}