package br.com.mydiagram.service

import br.com.mydiagram.controller.response.AuthenticationResponse
import br.com.mydiagram.enums.Errors
import br.com.mydiagram.exceptions.User.*
import br.com.mydiagram.model.MyDiagramUser
import br.com.mydiagram.repository.MyDiagramUserRepository
import br.com.mydiagram.security.JwtService
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import java.util.*

@Service
class MyDiagramUserService(
    private val myDiagramUserRepository: MyDiagramUserRepository,
    private val passwordEncoder: PasswordEncoder,
    private val jwtService: JwtService,
    private val authenticationManager: AuthenticationManager
) {

    fun login(email: String, pass: String): AuthenticationResponse {

        var user: MyDiagramUser? = null

        var jwtToken: String? = null

        if (!myDiagramUserRepository.existsByEmail(email))
            throw InexistentUserException(Errors.MDU0002.message, Errors.MDU0002.code)

        try {
            authenticationManager.authenticate(UsernamePasswordAuthenticationToken(email, pass))
        } catch (e: Exception){
            throw IncorrectPasswordException(Errors.MDU0003.message, Errors.MDU0003.code)
        }

        try {
            user = myDiagramUserRepository.findByEmail(email).orElseThrow()
            jwtToken = user?.let { jwtService.generateToken(it) }
        } catch (ex: Exception){
            throw UnexpectedLoginBehaviorException(Errors.MDU0004.message, Errors.MDU0004.code)
        }
        return AuthenticationResponse(jwtToken!!, myDiagramUserRepository.findByEmail(email).get().name)
    }

    fun signup(myDiagramUser: MyDiagramUser): AuthenticationResponse {

        var returnValue = false

        returnValue = try{

            val existentUser = myDiagramUserRepository.findByEmail(myDiagramUser.email)

            if (existentUser.get().email == myDiagramUser.email) {
                throw AlreadyExistentUserException(Errors.MDU0001.message, Errors.MDU0001.code)
            }
            true
        } catch (ex: NoSuchElementException){
            true
        }

        if (!returnValue){
           throw UnexpectedSignupBehaviorException(Errors.MDU0005.message, Errors.MDU0005.code)
        }

        val myDiagramEncryptedUser = MyDiagramUser(
            myDiagramUser.email,
            myDiagramUser.name,
            passwordEncoder.encode(myDiagramUser.pass),
        )

        myDiagramUserRepository.save(myDiagramEncryptedUser)

        val jwtToken = jwtService.generateToken(myDiagramEncryptedUser)
        return AuthenticationResponse(jwtToken, myDiagramUser.name)

    }

    fun getProfile(email: String): Optional<MyDiagramUser?> = myDiagramUserRepository.findByEmail(email)

    fun changeProfile(myDiagramUser: MyDiagramUser) {
        if (!myDiagramUserRepository.existsByEmail(myDiagramUser.email))
            throw InexistentUserException(Errors.MDU0002.message, Errors.MDU0002.code)

        val myDiagramEncryptedUser = MyDiagramUser(
                myDiagramUser.email,
                myDiagramUser.name,
                passwordEncoder.encode(myDiagramUser.pass),
            )

            myDiagramUserRepository.save(myDiagramEncryptedUser)
    }

    fun deleteProfile(email: String) {
        if (!myDiagramUserRepository.existsByEmail(email)) {
            throw InexistentUserException(Errors.MDU0002.message, Errors.MDU0002.code)
        }

        myDiagramUserRepository.deleteById(email)
    }
}