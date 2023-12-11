package br.com.mydiagram.controller

import br.com.mydiagram.controller.request.diagram.DeleteDiagramRequest
import br.com.mydiagram.controller.request.diagram.GetAllDiagramRequest
import br.com.mydiagram.controller.request.diagram.GetDiagramRequest
import br.com.mydiagram.controller.request.diagram.PostDiagramRequest
import br.com.mydiagram.model.Diagram
import br.com.mydiagram.service.DiagramService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*


@RestController
@RequestMapping("/api/v1/mydiagram/diagrams")
class DiagramController(
    val diagramService: DiagramService
) {

    @GetMapping("/getDiagrams")
    fun getAllDiagrams(@RequestBody getAllDiagramRequest: GetAllDiagramRequest) =
        ResponseEntity.ok(diagramService.getAllDiagrams(getAllDiagramRequest))

    @GetMapping("/getDiagram")
    fun getDiagram(@RequestBody getDiagramRequest: GetDiagramRequest) =
        ResponseEntity.ok(diagramService.getDiagram(getDiagramRequest))

    @PostMapping("/createDiagram")
    @ResponseStatus(HttpStatus.CREATED)
    fun createDiagram(@RequestBody diagram: Diagram) =
        diagramService.createDiagram(diagram)

    //TODO Conversar com o Vitor para remover essa daqui
    @PutMapping("/editDiagram/{diagramName}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun editDiagram(@PathVariable diagramName: String, @RequestBody userId: String) =
        diagramService.editDiagram(diagramName, userId)

    @DeleteMapping("/deleteDiagram/{userId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun deleteDiagram(@RequestBody deleteDiagramRequest: DeleteDiagramRequest, @PathVariable userId: String) =
        diagramService.deleteDiagram(deleteDiagramRequest, userId)

}