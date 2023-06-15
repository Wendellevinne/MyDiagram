package br.com.mydiagram.extensions

import br.com.mydiagram.controller.request.PostMyDiagramUserRequest
import br.com.mydiagram.controller.request.PutMyDiagramUserRequest
import br.com.mydiagram.enums.MyDiagramUserRoles
import br.com.mydiagram.model.MyDiagramUser
import br.com.mydiagram.repository.MyDiagramUserRepository

fun PostMyDiagramUserRequest.toMyDiagramUser(): MyDiagramUser = MyDiagramUser(
    email = this.email,
    fullName = this.fullName,
    password = this.password,
    role = MyDiagramUserRoles.OWNER
)

fun PutMyDiagramUserRequest.toMyDiagramUser(email: String): MyDiagramUser = MyDiagramUser(
    email = email,
    fullName = this.fullName,
    password = if (password == this.password) password else this.password,
    role = role ?: this.role
)