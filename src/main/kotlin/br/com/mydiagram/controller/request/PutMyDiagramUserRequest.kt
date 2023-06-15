package br.com.mydiagram.controller.request

import br.com.mydiagram.enums.MyDiagramUserRoles

data class PutMyDiagramUserRequest(var fullName: String, var password: String, var role: MyDiagramUserRoles?)