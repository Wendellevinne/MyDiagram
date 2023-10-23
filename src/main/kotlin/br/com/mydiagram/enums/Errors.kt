package br.com.mydiagram.enums

enum class Errors(val code: String, val message: String) {

    //Errors related with the signup/login process
    MDU0001("MDU0001", "Usuário já cadastrado"),
    MDU0002("MDU0002", "Usuário Inexistente"),
    MDU0003("MDU0003", "Usuário/Senha incorretos"),
    MDU0004("MDU0004", "Erro inesperado durante a operação de login"),
    MDU0005("MDU0005", "Erro inesperado durante a operação de cadastro"),

    //Errors related with the diagrammins process
    DGM0001("DGM0001", "O nome do diagrama não pode ser nulo"),
    DGM0002("DGM0002", "Já existe um diagrama salvo com este nome"),
    DGM0003("DGM0003", "Diagrama inexistente"),
    DGM0004("DGM0004", "Erro ao abrir o diagrama"),
    DGM0005("DGM0005", "Erro inesperado ao criar o diagrama"),
}