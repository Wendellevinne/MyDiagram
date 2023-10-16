package br.com.mydiagram.repository

import br.com.mydiagram.model.MyDiagramUser
import org.springframework.data.mongodb.repository.MongoRepository

interface DiagramRepository: MongoRepository<MyDiagramUser, String> {

}