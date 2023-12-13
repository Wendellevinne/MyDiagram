package br.com.mydiagram.controller.request.diagram

data class DeleteDiagramRequest(
    val name: String,
    val userId: String
)