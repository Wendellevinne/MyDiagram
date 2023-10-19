package br.com.mydiagram.exceptions.User

class UnexpectedSignupBehaviorException(override val message: String, val errorCode: String): Exception()