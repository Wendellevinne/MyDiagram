package br.com.mydiagram.controller.request.diagram

data class GetDiagramRequest(
    val path: String,
    val userId: String
)