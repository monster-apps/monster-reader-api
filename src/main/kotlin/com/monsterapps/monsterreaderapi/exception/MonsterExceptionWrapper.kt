package com.monsterapps.monsterreaderapi.exception

import com.monsterapps.monsterreaderapi.exception.MonsterExceptionType.TECHNICAL_EXCEPTION
import mu.KotlinLogging
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR
import org.springframework.http.ResponseEntity
import org.springframework.http.ResponseEntity.status

private val logger = KotlinLogging.logger {}

@ControllerAdvice
class MonsterExceptionWrapper {

    /**
     * Exception handler for the Throwable class. This method is the most generic error handler
     * intended to caught errors that were not handled properly in the application
     *
     * @param ex The Throwable exception class
     * @return Returns a custom JSON Payload containing a generic error along with 500 as HTTP status code
     */
    @ExceptionHandler(Throwable::class)
    fun handleUnknownException(ex: Throwable): ResponseEntity<MonsterExceptionResponse> {
        logger.error(ex) { "Got an exception due to: ${ex.message}" }
        val response = MonsterExceptionResponse(
            category = TECHNICAL_EXCEPTION.description,
            message = "An unexpected error has occurred")

        return status(INTERNAL_SERVER_ERROR).body(response)
    }
}