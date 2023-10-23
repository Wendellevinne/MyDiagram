package br.com.mydiagram.repository

import br.com.mydiagram.model.Diagram
import org.springframework.data.mongodb.repository.MongoRepository
import java.util.*

interface DiagramRepository: MongoRepository<Diagram, String> {

    fun findByNameAndAuthor(name: String, author: String): Diagram

    fun existsByNameAndAuthor(name: String, author: String): Boolean

    fun findAllByAuthor(author: String): Optional<List<Diagram>?>

}