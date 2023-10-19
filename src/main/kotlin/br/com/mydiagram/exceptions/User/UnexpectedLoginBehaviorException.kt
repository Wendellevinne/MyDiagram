package br.com.mydiagram.exceptions.User

class UnexpectedLoginBehaviorException(override val message: String, val errorCode: String): Exception()