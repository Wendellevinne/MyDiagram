package br.com.mydiagram.exceptions.User

class AlreadyExistentUserException(override val message: String, val errorCode: String): Exception()