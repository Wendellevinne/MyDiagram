package br.com.mydiagram.model

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import java.time.LocalDateTime

@Document("Diagrams")
data class Diagram(
    @Id
    val name: String,
    val createdDate: LocalDateTime? = LocalDateTime.now(),
    val modifiedDate: LocalDateTime? = LocalDateTime.now(),
    val listOfElements: List<Node>,
    val author: String
)
