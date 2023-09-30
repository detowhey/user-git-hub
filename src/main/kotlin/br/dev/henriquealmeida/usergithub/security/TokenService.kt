package br.dev.henriquealmeida.usergithub.security

import br.dev.henriquealmeida.usergithub.domain.User
import br.dev.henriquealmeida.usergithub.exception.JWTException
import com.auth0.jwt.JWT
import com.auth0.jwt.algorithms.Algorithm
import com.auth0.jwt.exceptions.JWTCreationException
import com.auth0.jwt.exceptions.JWTVerificationException
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneOffset

@Service
class TokenService {
    @Value("\${api.security.token.secret}")
    private lateinit var secret: String

    fun generateToken(user: User): String {
        try {
            return JWT.create()
                    .withIssuer("auth-api")
                    .withSubject(user.login)
                    .withExpiresAt(generateExpirationDate())
                    .sign(Algorithm.HMAC256(secret))
        } catch (e: JWTCreationException) {
            throw JWTException("Error while generating token", e.cause)
        }
    }

    fun validateToken(token: String): String {
        try {
            return JWT.require(Algorithm.HMAC256(secret))
                    .withIssuer("auth-api")
                    .build()
                    .verify(token)
                    .subject
        } catch (e: JWTVerificationException) {
            throw JWTException("Error while verifying token", e.cause)
        }
    }

    private fun generateExpirationDate(): Instant {
        return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00"))
    }
}