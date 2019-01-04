package application.extension.string

import application.config.mapper.objectMapper

fun <T> String.toJsonObject(valueType: Class<T>): T {
    return objectMapper().readValue(this, valueType)
}