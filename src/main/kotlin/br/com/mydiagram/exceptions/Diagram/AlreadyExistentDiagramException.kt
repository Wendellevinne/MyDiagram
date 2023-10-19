package br.com.mydiagram.exceptions.Diagram

class AlreadyExistentDiagramException (override val message: String, val errorCode: String): Exception()