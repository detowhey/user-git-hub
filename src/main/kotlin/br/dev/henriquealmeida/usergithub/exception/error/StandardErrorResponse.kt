package br.dev.henriquealmeida.usergithub.exception.error

import com.fasterxml.jackson.annotation.JsonFormat
import java.time.Instant

data class StandardErrorResponse(
    @field:JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm:ss", timezone = "UTC")
    val timeStamp: Instant,
    val statusCode: Int,
    val error: String,
    val message: String?,
    val path: String
)
