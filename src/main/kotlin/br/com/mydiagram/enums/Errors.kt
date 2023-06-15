package br.com.mydiagram.enums

enum class Errors(val code: String, val message: String) {

    MDU0001("MDU0001", "Usuário já cadastrado"),
    MDU0002("MDU0002", "Usuário Inexistente"),
    MDU0003("MDU0003", "Usuário/Senha incorretos"),
    MDU0004("MDU0004", "Erro inesperado durante a operação de login")
}