package br.dev.henriquealmeida.usergithub.exception

import java.time.DateTimeException

class InvalidDateException(override val message: String? = "Date format is invalid") : DateTimeException(message) {
    constructor(cause: Throwable) : this() {
        throw InvalidDateException(message, cause)
    }

    constructor(message: String?, cause: Throwable) : this() {
        throw InvalidDateException(message, cause)
    }
}
