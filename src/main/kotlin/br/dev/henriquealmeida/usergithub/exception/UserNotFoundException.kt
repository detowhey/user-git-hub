package br.dev.henriquealmeida.usergithub.exception

class UserNotFoundException(override val message: String? = "User not found") : RuntimeException(message)