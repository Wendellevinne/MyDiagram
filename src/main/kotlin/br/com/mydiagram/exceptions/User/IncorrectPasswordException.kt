package br.com.mydiagram.exceptions.User

data class IncorrectPasswordException(override val message: String, val errorCode: String): Exception()