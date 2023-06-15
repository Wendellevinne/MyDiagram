package br.com.mydiagram.exceptions

data class IncorrectPasswordException(override val message: String, val errorCode: String): Exception()