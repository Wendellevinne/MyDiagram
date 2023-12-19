package br.com.mydiagram.extensions

import br.com.mydiagram.controller.request.user.PostMyDiagramUserRequest
import br.com.mydiagram.controller.request.user.PutMyDiagramUserRequest
import br.com.mydiagram.enums.MyDiagramUserRoles
import br.com.mydiagram.model.MyDiagramUser

fun PostMyDiagramUserRequest.toMyDiagramUser(): MyDiagramUser = MyDiagramUser(
    email = this.email,
    name = this.name,
    pass = this.pass,
)

fun PutMyDiagramUserRequest.toMyDiagramUser(email: String): MyDiagramUser = MyDiagramUser(
    email = email,
    name = this.name,
    pass = if (pass == this.pass) pass else this.pass,
)