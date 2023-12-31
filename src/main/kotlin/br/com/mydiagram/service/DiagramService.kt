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

        if (!diagramRepository.existsByPathAndUserId(getDiagramRequest.path, getDiagramRequest.userId))
            throw InexistentDiagramException(Errors.DGM0003.code, Errors.DGM0003.message)

        try {
            selectedDiagram = diagramRepository.findByPathAndUserId(getDiagramRequest.path, getDiagramRequest.userId)
            selectedDiagram.file = fileService.downloadFile(selectedDiagram.path)
        } catch (ex: Exception){
            throw CouldNotOpenDiagramException(Errors.DGM0004.code, Errors.DGM0004.message)
        }

        return selectedDiagram
    }

    fun createDiagram(postDiagramRequest: PostDiagramRequest): Diagram {

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
            return newDiagram
        } catch (ex: Exception){
            throw CouldNotCreateDiagramException(Errors.DGM0005.code, Errors.DGM0005.message)
        }


    }

    fun editDiagram(editDiagramRequest: EditDiagramRequest) {

        if (!diagramRepository.existsByPathAndUserId(editDiagramRequest.path, editDiagramRequest.userId))
            throw InexistentDiagramException(Errors.DGM0003.code, Errors.DGM0003.message)

        val selectedDiagram = getDiagram(
            GetDiagramRequest(editDiagramRequest.path, editDiagramRequest.userId)
        )

        val newContent = fileService.fileConversorToString(fileService.fileConversorToInputStreamResource(editDiagramRequest.diagram))

        if (selectedDiagram.file != newContent ||
            !editDiagramRequest.newName.isNullOrBlank() && selectedDiagram.name != editDiagramRequest.newName){

            val editedDiagram = Diagram(
                name = editDiagramRequest.newName ?: selectedDiagram.name,
                path = selectedDiagram.path,
                userId = selectedDiagram.userId,
            )

            try {
                fileService.updateFile(editDiagramRequest.diagram, selectedDiagram.path)
                logger.debug { "Arquivo enviado com sucesso!" }
            } catch (ex:Exception){
                throw Exception()
            }

            diagramRepository.save(editedDiagram)
        }

    }

    fun deleteDiagram(deleteDiagramRequest: DeleteDiagramRequest){

        if (!diagramRepository.existsByPathAndUserId(deleteDiagramRequest.path, deleteDiagramRequest.userId))
            throw InexistentDiagramException(Errors.DGM0003.code, Errors.DGM0003.message)

        if (!fileService.deleteFile(deleteDiagramRequest.path)){
            throw Exception("Não foi possível deletar o arquivo")
        }

        val selectedDiagram = diagramRepository
            .findByPathAndUserId(deleteDiagramRequest.path, deleteDiagramRequest.userId)

        diagramRepository.deleteByPathAndUserId(selectedDiagram.path, selectedDiagram.userId)

    }


}