package br.com.mydiagram.controller

import br.com.mydiagram.controller.request.diagram.GetDiagramRequest
import br.com.mydiagram.controller.request.diagram.PostDiagramRequest
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
    fun getAllDiagrams(@RequestBody author: String) =
        ResponseEntity.ok(diagramService.getAllDiagrams(author))

    @GetMapping("/getDiagram")
    fun getDiagram(@RequestBody getDiagramRequest: GetDiagramRequest) =
        ResponseEntity.ok(diagramService.getDiagram(getDiagramRequest))

    @PostMapping("/createDiagram")
    @ResponseStatus(HttpStatus.CREATED)
    fun createDiagram(@RequestBody postDiagramRequest: PostDiagramRequest) =
        diagramService.createDiagram(postDiagramRequest)

    @PutMapping("/editDiagram/{diagramName}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun editDiagram(@PathVariable diagramName: String, @RequestBody author: String) =
        diagramService.editDiagram(diagramName, author)

    @DeleteMapping("/deleteDiagram/{diagramName}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun deleteDiagram(@PathVariable diagramName: String, @RequestBody author: String) =
        diagramService.deleteDiagram(diagramName, author)

}