package br.com.mydiagram.exceptions.User

class InexistentUserException(override val message: String, val errorCode: String): Exception()