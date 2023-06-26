package br.dev.henriquealmeida.usergithub.exception

class UserNotFoundException(message: String? = "User not found", cause: Throwable?) : RuntimeException(message, cause)
