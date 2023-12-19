package br.com.mydiagram.controller.request.user

import br.com.mydiagram.enums.MyDiagramUserRoles

data class PutMyDiagramUserRequest(var name: String, var pass: String, var role: MyDiagramUserRoles?)