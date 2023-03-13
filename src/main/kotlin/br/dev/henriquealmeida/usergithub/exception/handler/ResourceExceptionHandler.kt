package br.dev.henriquealmeida.usergithub.exception.handler

import br.dev.henriquealmeida.usergithub.exception.InvalidDateException
import br.dev.henriquealmeida.usergithub.exception.error.StandardErrorResponse
import feign.FeignException
import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import java.time.Instant
import javax.servlet.http.HttpServletRequest

@ControllerAdvice
class ResourceExceptionHandler {

    private val logger = LoggerFactory.getLogger(ResourceExceptionHandler::class.java)

    @ExceptionHandler(InvalidDateException::class)
    fun sendInvalidDateException(
        exception: InvalidDateException,
        request: HttpServletRequest
    ): ResponseEntity<StandardErrorResponse> =
        buildResponseErrorEntity(HttpStatus.BAD_REQUEST, exception, request, "Invalid date")

    @ExceptionHandler(FeignException.NotFound::class)
    fun sendErrorFeignCliente(
        exception: FeignException.FeignServerException,
        request: HttpServletRequest
    ): ResponseEntity<StandardErrorResponse> =
        buildResponseErrorEntity(HttpStatus.NOT_FOUND, exception, request, "Client GitHub not found")


    private fun buildResponseErrorEntity(
        httpStatus: HttpStatus,
        exception: Exception,
        request: HttpServletRequest,
        messageError: String
    ): ResponseEntity<StandardErrorResponse> {
        return StandardErrorResponse(
            Instant.now(),
            httpStatus.value(),
            messageError,
            exception.message,
            request.requestURI
        ).let {
            ResponseEntity.status(httpStatus).body(it)
        }.also {
            this.logger.error(exception.message, exception)
        }
    }
}
