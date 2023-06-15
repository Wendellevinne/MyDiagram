package br.com.mydiagram.utils

import jakarta.xml.bind.DatatypeConverter
import java.security.MessageDigest

private const val ENCODING = "SHA-256"

class Encrypter {

    fun encrypt(string: String): String{
        val bytes = MessageDigest.getInstance(ENCODING).digest(string.toByteArray())
        return DatatypeConverter.printHexBinary(bytes)
    }

    fun comparePasswords(password: String, hashedPassword: String): Boolean =
        encrypt(password) == hashedPassword

}