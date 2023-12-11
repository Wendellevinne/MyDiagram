package br.com.mydiagram.model

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@Document("Diagrams")
data class Diagram(
    @Id
    val name: String,
    val path: String,
    val userId: String
)
