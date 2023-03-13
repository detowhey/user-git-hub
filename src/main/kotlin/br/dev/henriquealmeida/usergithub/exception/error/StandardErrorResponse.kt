package br.dev.henriquealmeida.usergithub.exception.error

import com.fasterxml.jackson.annotation.JsonFormat
import java.time.Instant

data class StandardErrorResponse(
    @field:JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm:ss", timezone = "UTC")
    private val timeStamp: Instant,
    private val statusCode: Int,
    private val error: String,
    private val message: String?,
    private val path: String
)
