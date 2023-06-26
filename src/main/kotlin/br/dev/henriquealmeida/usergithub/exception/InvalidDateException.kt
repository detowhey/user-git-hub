package br.dev.henriquealmeida.usergithub.exception

import java.time.DateTimeException

class InvalidDateException(message: String = "Date format is invalid", cause: Throwable?) : DateTimeException(message, cause)
