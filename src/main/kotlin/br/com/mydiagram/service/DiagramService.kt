package br.com.mydiagram.service

import br.com.mydiagram.controller.request.diagram.DeleteDiagramRequest
import br.com.mydiagram.controller.request.diagram.GetAllDiagramRequest
import br.com.mydiagram.controller.request.diagram.GetDiagramRequest
import br.com.mydiagram.enums.Errors
import br.com.mydiagram.exceptions.Diagram.*
import br.com.mydiagram.model.Diagram
import br.com.mydiagram.repository.DiagramRepository
import mu.KotlinLogging
import org.springframework.stereotype.Service
import java.util.*

@Service
class DiagramService(
    val diagramRepository: DiagramRepository
) {

    private val logger = KotlinLogging.logger {}

    fun getAllDiagrams(getAllDiagramRequest: GetAllDiagramRequest): Optional<List<Diagram>?> =
        diagramRepository.findAllByUserId(getAllDiagramRequest.userId)

    fun getDiagram(getDiagramRequest: GetDiagramRequest): Diagram {

        val selectedDiagram: Diagram

        if (!diagramRepository.existsByNameAndUserId(getDiagramRequest.name, getDiagramRequest.userId))
            throw InexistentDiagramException(Errors.DGM0003.code, Errors.DGM0003.message)

        try {
            selectedDiagram = diagramRepository.findByNameAndUserId(getDiagramRequest.name, getDiagramRequest.userId)
        } catch (ex: Exception){
            throw CouldNotOpenDiagramException(Errors.DGM0004.code, Errors.DGM0004.message)
        }

        return selectedDiagram
    }

    fun createDiagram(diagram: Diagram){

        val newDiagram: Diagram

        if (diagram.name.trim() == "")
            throw NullDiagramNameException(Errors.DGM0001.code, Errors.DGM0001.message)

        if (diagramRepository.existsByNameAndUserId(diagram.name, diagram.userId))
            throw AlreadyExistentDiagramException(Errors.DGM0002.code, Errors.DGM0002.message)

        try {
            newDiagram = Diagram(
                name = diagram.name,
                path = diagram.path,
                userId = diagram.userId
            )
            diagramRepository.save(newDiagram)
        } catch (ex: Exception){
            throw CouldNotCreateDiagramException(Errors.DGM0005.code, Errors.DGM0005.message)
        }
    }

    fun editDiagram(diagramName: String, userId: String){

    }

    fun deleteDiagram(deleteDiagramRequest: DeleteDiagramRequest, userId: String){

        logger.debug { "Os valores recebidos foram ${deleteDiagramRequest.name} e $userId" }

        if (deleteDiagramRequest.name.trim() == "")
            throw NullDiagramNameException(Errors.DGM0001.code, Errors.DGM0001.message)

        if (!diagramRepository.existsByNameAndUserId(deleteDiagramRequest.name, userId))
            throw InexistentDiagramException(Errors.DGM0003.code, Errors.DGM0003.message)

        diagramRepository.delete(diagramRepository.findByNameAndUserId(deleteDiagramRequest.name, userId))
    }


}