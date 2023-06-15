package br.com.mydiagram.model

import br.com.mydiagram.enums.MyDiagramUserRoles
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@Document("MyDiagramUsers")
data class MyDiagramUser(
    @Id
    val email: String,
    val fullName: String?,
    val password: String,
    val role: MyDiagramUserRoles?
)