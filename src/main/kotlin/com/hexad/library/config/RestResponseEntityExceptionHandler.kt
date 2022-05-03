package com.hexad.library.config

import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.context.request.WebRequest
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler

@ControllerAdvice
class RestResponseEntityExceptionHandler : ResponseEntityExceptionHandler() {
    @ExceptionHandler(value = [Exception::class])
    fun handleConflict(
        ex: RuntimeException?, request: WebRequest?
    ): ResponseEntity<Any> {
        val bodyOfResponse = "An error occurs: ${ex?.message ?: "Unknown error."}"
        return handleExceptionInternal(ex, bodyOfResponse, HttpHeaders(), HttpStatus.BAD_REQUEST, request)
    }
}