package br.com.mydiagram.exceptions.Diagram

class CouldNotOpenDiagramException(override val message: String, val errorCode: String): Exception()