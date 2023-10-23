package br.com.mydiagram.service

import br.com.mydiagram.controller.request.diagram.GetDiagramRequest
import br.com.mydiagram.controller.request.diagram.PostDiagramRequest
import br.com.mydiagram.enums.Errors
import br.com.mydiagram.exceptions.Diagram.*
import br.com.mydiagram.model.Diagram
import br.com.mydiagram.repository.DiagramRepository
import org.springframework.stereotype.Service
import java.util.*

@Service
class DiagramService(
    val diagramRepository: DiagramRepository
) {

    fun getAllDiagrams(author: String): Optional<List<Diagram>?> = diagramRepository.findAllByAuthor(author)

    fun getDiagram(getDiagramRequest: GetDiagramRequest): Diagram {

        val selectedDiagram: Diagram

        if (!diagramRepository.existsByNameAndAuthor(getDiagramRequest.name, getDiagramRequest.author))
            throw InexistentDiagramException(Errors.DGM0003.code, Errors.DGM0003.message)

        try {
            selectedDiagram = diagramRepository.findByNameAndAuthor(getDiagramRequest.name, getDiagramRequest.author)
        } catch (ex: Exception){
            throw CouldNotOpenDiagramException(Errors.DGM0004.code, Errors.DGM0004.message)
        }

        return selectedDiagram
    }

    fun createDiagram(postDiagramRequest: PostDiagramRequest){

        val newDiagram: Diagram

        if (postDiagramRequest.name.trim() == "")
            throw NullDiagramNameException(Errors.DGM0001.code, Errors.DGM0001.message)

        if (diagramRepository.existsByNameAndAuthor(postDiagramRequest.name, postDiagramRequest.author))
            throw AlreadyExistentDiagramException(Errors.DGM0002.code, Errors.DGM0002.message)

        try {
            //TODO Transformar em uma ExtensionFunction
            newDiagram = Diagram(
                name = postDiagramRequest.name,
                createdDate = null,
                modifiedDate = null,
                listOfElements = mutableListOf(),
                author = postDiagramRequest.author
            )
            diagramRepository.save(newDiagram)
        } catch (ex: Exception){
            throw CouldNotCreateDiagramException(Errors.DGM0005.code, Errors.DGM0005.message)
        }
    }

    fun editDiagram(diagramName: String, author: String){

    }

    fun deleteDiagram(diagramName: String, author: String){

        if (diagramName.trim() == "")
            throw NullDiagramNameException(Errors.DGM0001.code, Errors.DGM0001.message)

        if (!diagramRepository.existsByNameAndAuthor(diagramName, author))
            throw InexistentDiagramException(Errors.DGM0003.code, Errors.DGM0003.message)

        diagramRepository.delete(diagramRepository.findByNameAndAuthor(diagramName, author))
    }


}