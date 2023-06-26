package br.dev.henriquealmeida.usergithub.exception

class UserNotFoundException(override val message: String? = "User not found") : RuntimeException(message) {

    constructor(cause: Throwable) : this() {
        throw UserNotFoundException(message, cause)
    }

    constructor(message: String?, cause: Throwable) : this() {
        throw UserNotFoundException(message, cause)
    }
}
