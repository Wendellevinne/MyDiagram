package br.com.mydiagram.controller.request.diagram

import org.springframework.web.multipart.MultipartFile

data class PostDiagramRequest(
    val name: String,
    val userId: String,
    val diagram: MultipartFile
)