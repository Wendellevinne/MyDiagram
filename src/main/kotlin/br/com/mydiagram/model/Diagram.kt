package br.com.mydiagram.model

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@Document("Diagrams")
data class Diagram(
    val name: String,
    @Id
    val path: String,
    val userId: String,
    var file: String? = null
)
