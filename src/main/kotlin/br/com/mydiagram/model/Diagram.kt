package br.com.mydiagram.model

import org.springframework.core.io.InputStreamResource
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import org.springframework.web.multipart.MultipartFile

@Document("Diagrams")
data class Diagram(
    val name: String,
    @Id
    val path: String,
    val userId: String,
    var file: String? = null
)
