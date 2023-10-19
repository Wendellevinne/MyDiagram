package br.com.mydiagram.exceptions.Diagram

class InexistentDiagramException(override val message: String, val errorCode: String): Exception()