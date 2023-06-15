package br.com.mydiagram.exceptions

class UnexpectedSignupBehaviorException(override val message: String, val errorCode: String): Exception()