package br.com.mydiagram.exceptions.Diagram

import br.com.mydiagram.controller.response.ErrorResponse
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.context.request.WebRequest

@ControllerAdvice
class DiagramControllerAdvice {

    @ExceptionHandler(AlreadyExistentDiagramException::class)
    fun handleAlreadyExistentDiagramException(ex: AlreadyExistentDiagramException, request: WebRequest): ResponseEntity<ErrorResponse>{
        val error = ErrorResponse(
            HttpStatus.BAD_REQUEST.value(),
            ex.message,
            ex.errorCode,
            null
        )

        return ResponseEntity(error, HttpStatus.BAD_REQUEST)
    }

    @ExceptionHandler(CouldNotCreateDiagramException::class)
    fun handleCouldNotCreateDiagramException(ex: CouldNotCreateDiagramException, request: WebRequest): ResponseEntity<ErrorResponse>{
        val error = ErrorResponse(
            HttpStatus.BAD_REQUEST.value(),
            ex.message,
            ex.errorCode,
            null
        )

        return ResponseEntity(error, HttpStatus.BAD_REQUEST)
    }

    @ExceptionHandler(CouldNotOpenDiagramException::class)
    fun handleCouldNotOpenDiagramException(ex: CouldNotOpenDiagramException, request: WebRequest): ResponseEntity<ErrorResponse>{
        val error = ErrorResponse(
            HttpStatus.BAD_REQUEST.value(),
            ex.message,
            ex.errorCode,
            null
        )

        return ResponseEntity(error, HttpStatus.BAD_REQUEST)
    }

    @ExceptionHandler(InexistentDiagramException::class)
    fun handleInexistentDiagramException(ex: InexistentDiagramException, request: WebRequest): ResponseEntity<ErrorResponse>{
        val error = ErrorResponse(
            HttpStatus.BAD_REQUEST.value(),
            ex.message,
            ex.errorCode,
            null
        )

        return ResponseEntity(error, HttpStatus.BAD_REQUEST)
    }

}