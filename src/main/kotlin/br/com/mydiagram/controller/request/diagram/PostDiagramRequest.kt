package br.com.mydiagram.controller.request.diagram

import br.com.mydiagram.model.Node

data class PostDiagramRequest(
    val name: String,
    val author: String
)