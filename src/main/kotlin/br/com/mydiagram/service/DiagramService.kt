package br.com.mydiagram.service

import br.com.mydiagram.controller.request.diagram.DeleteDiagramRequest
import br.com.mydiagram.controller.request.diagram.EditDiagramRequest
import br.com.mydiagram.controller.request.diagram.GetDiagramRequest
import br.com.mydiagram.controller.request.diagram.PostDiagramRequest
import br.com.mydiagram.enums.Errors
import br.com.mydiagram.exceptions.Diagram.*
import br.com.mydiagram.model.Diagram
import br.com.mydiagram.repository.DiagramRepository
import mu.KotlinLogging
import org.springframework.stereotype.Service
import java.util.*

@Service
class DiagramService(
    val fileService: FileService,
    val diagramRepository: DiagramRepository
) {

    private val logger = KotlinLogging.logger {}

    fun getAllDiagrams(userId: String): Optional<List<Diagram>?> =
        diagramRepository.findAllByUserId(userId)



    fun getDiagram(getDiagramRequest: GetDiagramRequest): Diagram {

        val selectedDiagram: Diagram

        if (!diagramRepository.existsByNameAndUserId(getDiagramRequest.name, getDiagramRequest.userId))
            throw InexistentDiagramException(Errors.DGM0003.code, Errors.DGM0003.message)

        try {
            selectedDiagram = diagramRepository.findByNameAndUserId(getDiagramRequest.name, getDiagramRequest.userId)
            selectedDiagram.file = fileService.downloadFile(selectedDiagram.path)
        } catch (ex: Exception){
            throw CouldNotOpenDiagramException(Errors.DGM0004.code, Errors.DGM0004.message)
        }

        return selectedDiagram
    }

    fun createDiagram(postDiagramRequest: PostDiagramRequest){

        val newDiagram: Diagram

        if (postDiagramRequest.name.trim() == "")
            throw NullDiagramNameException(Errors.DGM0001.code, Errors.DGM0001.message)

        if (diagramRepository.existsByNameAndUserId(postDiagramRequest.name, postDiagramRequest.userId))
            throw AlreadyExistentDiagramException(Errors.DGM0002.code, Errors.DGM0002.message)

        val filePath = fileService.uploadFile(postDiagramRequest.diagram)

        try {
            newDiagram = Diagram(
                name = postDiagramRequest.name,
                path = filePath,
                userId = postDiagramRequest.userId
            )
            diagramRepository.save(newDiagram)
        } catch (ex: Exception){
            throw CouldNotCreateDiagramException(Errors.DGM0005.code, Errors.DGM0005.message)
        }


    }

    fun editDiagram(editDiagramRequest: EditDiagramRequest) {

        if (editDiagramRequest.name.trim() == "")
            throw NullDiagramNameException(Errors.DGM0001.code, Errors.DGM0001.message)

        if (diagramRepository.existsByNameAndUserId(editDiagramRequest.name, editDiagramRequest.userId))
            throw AlreadyExistentDiagramException(Errors.DGM0002.code, Errors.DGM0002.message)

        val selectedDiagram = getDiagram(
            GetDiagramRequest(editDiagramRequest.name, editDiagramRequest.userId)
        )

        val newContent = fileService.fileConversorToString(fileService.fileConversorToInputStreamResource(editDiagramRequest.diagram))

        if (selectedDiagram.file != newContent ||
            selectedDiagram.name != editDiagramRequest.name){

            val editedDiagram = Diagram(
                name = editDiagramRequest.name,
                path = selectedDiagram.path,
                userId = selectedDiagram.userId,
            )

            fileService.updateFile(editDiagramRequest.diagram)
        }

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