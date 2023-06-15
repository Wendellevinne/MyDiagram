package br.com.mydiagram.exceptions

class InexistentUserException(override val message: String, val errorCode: String): Exception()