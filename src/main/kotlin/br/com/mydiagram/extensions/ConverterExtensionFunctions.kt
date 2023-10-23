package br.com.mydiagram.extensions

import br.com.mydiagram.controller.request.user.PostMyDiagramUserRequest
import br.com.mydiagram.controller.request.user.PutMyDiagramUserRequest
import br.com.mydiagram.enums.MyDiagramUserRoles
import br.com.mydiagram.model.MyDiagramUser

fun PostMyDiagramUserRequest.toMyDiagramUser(): MyDiagramUser = MyDiagramUser(
    email = this.email,
    fullName = this.fullName,
    pass = this.pass,
    myDiagramUserRoles = MyDiagramUserRoles.OWNER
)

fun PutMyDiagramUserRequest.toMyDiagramUser(email: String): MyDiagramUser = MyDiagramUser(
    email = email,
    fullName = this.fullName,
    pass = if (pass == this.pass) pass else this.pass,
    myDiagramUserRoles = role ?: this.role
)