package br.com.mydiagram.service

import br.com.mydiagram.enums.Errors
import br.com.mydiagram.exceptions.AlreadyExistentUserException
import br.com.mydiagram.exceptions.IncorrectPasswordException
import br.com.mydiagram.exceptions.InexistentUserException
import br.com.mydiagram.exceptions.UnexpectedSignupBehaviorException
import br.com.mydiagram.model.MyDiagramUser
import br.com.mydiagram.repository.MyDiagramUserRepository
import br.com.mydiagram.utils.Encrypter
import org.springframework.stereotype.Service
import java.util.*

@Service
class MyDiagramUserService(val myDiagramUserRepository: MyDiagramUserRepository) {

    //TODO Testar o código do login
    fun login(email: String, password: String): Optional<MyDiagramUser?> {
        if (!myDiagramUserRepository.existsByEmail(email))
            throw InexistentUserException(Errors.MDU0002.message, Errors.MDU0002.code)

        val comparator = myDiagramUserRepository.findByEmail(email)

        return if (!Encrypter().comparePasswords(comparator.get().password, password)) {
            throw IncorrectPasswordException(Errors.MDU0003.message, Errors.MDU0003.code)
        } else {
            myDiagramUserRepository.findByEmail(email)
        }
    }

    fun signup(myDiagramUser: MyDiagramUser) {

        var returnValue = false

        try{

            val existentUser = myDiagramUserRepository.findByEmail(myDiagramUser.email)

            if (existentUser.get().email == myDiagramUser.email) {
                throw AlreadyExistentUserException(Errors.MDU0001.message, Errors.MDU0001.code)
            }

            returnValue = true

        } catch (ex: NoSuchElementException){

            val myDiagramEncryptedUser = MyDiagramUser(
                myDiagramUser.email, myDiagramUser.fullName,
                Encrypter().encrypt(myDiagramUser.password), myDiagramUser.role
            )

            myDiagramUserRepository.save(myDiagramEncryptedUser)
            returnValue = true

        }

        if (!returnValue){
           throw UnexpectedSignupBehaviorException(Errors.MDU0004.message, Errors.MDU0004.code)
        }

    }

    fun changeProfile(myDiagramUser: MyDiagramUser) {
        if (!myDiagramUserRepository.existsByEmail(myDiagramUser.email))
            throw InexistentUserException(Errors.MDU0002.message, Errors.MDU0002.code)

        if (Encrypter().comparePasswords(myDiagramUser.password, myDiagramUserRepository.findByEmail(myDiagramUser.email).get().password)){
            val myDiagramEncryptedUser = MyDiagramUser(
                myDiagramUser.email, myDiagramUser.fullName,
                Encrypter().encrypt(myDiagramUser.password), myDiagramUser.role
            )

            myDiagramUserRepository.save(myDiagramEncryptedUser)
        } else {
            myDiagramUserRepository.save(myDiagramUser)
        }
    }

    fun deleteProfile(email: String) {
        if (!myDiagramUserRepository.existsByEmail(email)) {
            throw InexistentUserException(Errors.MDU0002.message, Errors.MDU0002.code)
        }

        myDiagramUserRepository.deleteById(email)
    }
}