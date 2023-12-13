package br.com.mydiagram.controller.request.diagram

import org.springframework.web.multipart.MultipartFile

data class EditDiagramRequest(
    val path: String,
    val newName: String?,
    val userId: String,
    val diagram: MultipartFile
)
