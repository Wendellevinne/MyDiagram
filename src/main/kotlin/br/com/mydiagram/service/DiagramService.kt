package br.com.mydiagram.service

import br.com.mydiagram.model.Diagram
import br.com.mydiagram.repository.DiagramRepository
import org.springframework.stereotype.Service

@Service
class DiagramService(
    diagramRepository: DiagramRepository
) {

    fun getAllDiagrams(userEmail: String): List<Diagram>{

        return emptyList<Diagram>()
    }

    fun createDiagram(){

    }

    fun editDiagram(diagramName: String, email: String){

    }

    fun deleteDiagram(diagramName: String, email: String){

    }


}