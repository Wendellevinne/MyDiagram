package br.com.mydiagram.repository

import br.com.mydiagram.model.MyDiagramUser
import org.springframework.data.mongodb.repository.MongoRepository
import java.util.*

interface MyDiagramUserRepository: MongoRepository<MyDiagramUser, String> {

    fun findByEmail(email: String): Optional<MyDiagramUser?>

    fun existsByEmail(email: String): Boolean

}