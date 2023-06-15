package br.com.mydiagram.exceptions

class AlreadyExistentUserException(override val message: String, val errorCode: String): Exception()