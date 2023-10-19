package br.com.mydiagram.model

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@Document("Elements")
data class Node(
    @Id
    val key: Int,
    val name: String,
    val properties: Property?,
    val methods: Method?
)
