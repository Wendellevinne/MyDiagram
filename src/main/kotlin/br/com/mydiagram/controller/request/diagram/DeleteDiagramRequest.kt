package br.com.mydiagram.controller.request.diagram

data class DeleteDiagramRequest(
    val path: String,
    val userId: String
)