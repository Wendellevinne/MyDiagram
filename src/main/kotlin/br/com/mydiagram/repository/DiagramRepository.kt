package br.com.mydiagram.repository

import br.com.mydiagram.model.Diagram
import org.springframework.data.mongodb.repository.MongoRepository
import java.util.*

interface DiagramRepository: MongoRepository<Diagram, String> {

    fun existsByNameAndUserId(name: String, userId: String): Boolean

    fun findByPathAndUserId(path: String, userId: String): Diagram

    fun existsByPathAndUserId(path: String, userId: String): Boolean

    fun findAllByUserId(userId: String): Optional<List<Diagram>?>

}