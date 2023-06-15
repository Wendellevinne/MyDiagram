package br.com.mydiagram.controller.response

data class FieldErrorResponse(
    var message: String,
    var field: String
)