package br.com.mydiagram.exceptions.Diagram

class CouldNotCreateDiagramException(override val message: String, val errorCode: String): Exception()