package application.config.mapper

import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper

fun objectMapper(): ObjectMapper {
    return jacksonObjectMapper().setSerializationInclusion(JsonInclude.Include.NON_NULL)
}