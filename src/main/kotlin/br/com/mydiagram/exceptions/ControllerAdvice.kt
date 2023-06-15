package br.com.mydiagram.exceptions

import br.com.mydiagram.controller.response.ErrorResponse
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.context.request.WebRequest

@ControllerAdvice
class ControllerAdvice {

    @ExceptionHandler(AlreadyExistentUserException::class)
    fun handleAlreadyExistentUserException(ex: AlreadyExistentUserException, request: WebRequest): ResponseEntity<ErrorResponse>{
        val error = ErrorResponse(
            HttpStatus.BAD_REQUEST.value(),
            ex.message,
            ex.errorCode,
            null
        )

        return ResponseEntity(error, HttpStatus.BAD_REQUEST)
    }

    @ExceptionHandler(InexistentUserException::class)
    fun handleInexistentUserException(ex: InexistentUserException, request: WebRequest): ResponseEntity<ErrorResponse>{
        val error = ErrorResponse(
            HttpStatus.BAD_REQUEST.value(),
            ex.message,
            ex.errorCode,
            null
        )

        return ResponseEntity(error, HttpStatus.BAD_REQUEST)
    }

    @ExceptionHandler(IncorrectPasswordException::class)
    fun handleIncorretPasswordException(ex: IncorrectPasswordException, request: WebRequest): ResponseEntity<ErrorResponse>{
        val error = ErrorResponse(
            HttpStatus.FORBIDDEN.value(),
            ex.message,
            ex.errorCode,
            null
        )

        return ResponseEntity(error, HttpStatus.FORBIDDEN)
    }

    @ExceptionHandler(UnexpectedSignupBehaviorException::class)
    fun handleUnexpectedSignupBehaviorException(ex: UnexpectedSignupBehaviorException, request: WebRequest): ResponseEntity<ErrorResponse>{
        val error = ErrorResponse(
            HttpStatus.BAD_REQUEST.value(),
            ex.message,
            ex.errorCode,
            null
        )

        return ResponseEntity(error, HttpStatus.BAD_REQUEST)
    }


}