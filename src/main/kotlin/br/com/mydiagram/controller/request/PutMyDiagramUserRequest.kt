package br.com.mydiagram.controller.request

import br.com.mydiagram.enums.MyDiagramUserRoles

data class PutMyDiagramUserRequest(var fullName: String, var pass: String, var role: MyDiagramUserRoles?)