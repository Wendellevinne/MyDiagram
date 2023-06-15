package br.com.mydiagram.model

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@Document("Elements")
data class Node(
    @Id
    val id: Int,
    val typeElement: String,
    val position: Position,
    val height: Double,
    val weight: Double
)
