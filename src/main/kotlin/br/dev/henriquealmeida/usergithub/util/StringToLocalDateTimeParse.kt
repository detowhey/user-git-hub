package br.dev.henriquealmeida.usergithub.util

import br.dev.henriquealmeida.usergithub.exception.InvalidDateException
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

fun String.parseToLocalDateTime(pattern: String = "dd/MM/yyyy HH:mm"): LocalDateTime {
    try {
        return LocalDateTime.parse(this, DateTimeFormatter.ofPattern(pattern))
    } catch (e: Exception) {
        throw InvalidDateException(e)
    }
}
