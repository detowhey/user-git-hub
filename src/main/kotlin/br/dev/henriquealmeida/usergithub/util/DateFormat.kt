package br.dev.henriquealmeida.usergithub.util

import br.dev.henriquealmeida.usergithub.exception.InvalidDateException
import java.time.OffsetDateTime
import java.time.format.DateTimeFormatter

fun String.applyDateFormat(pattern: String = "dd/MM/yyyy HH:mm"): String {
    try {
        return OffsetDateTime.parse(this).format(DateTimeFormatter.ofPattern(pattern))
    } catch (e: Exception) {
        throw InvalidDateException(cause = e.cause)
    }
}
