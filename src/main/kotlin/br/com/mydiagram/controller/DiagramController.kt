package br.com.mydiagram.controller

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
    fun getAllDiagrams(@RequestBody userEmail: String) =
        ResponseEntity.ok(diagramService.getAllDiagrams(userEmail))

    @PostMapping("/createDiagram")
    @ResponseStatus(HttpStatus.CREATED)
    fun createDiagram(@RequestBody userEmail: String) =
        diagramService.createDiagram()

    @PutMapping("/editDiagram/{diagramName}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun editDiagram(@PathVariable diagramName: String, @RequestBody userEmail: String) =
        diagramService.editDiagram(diagramName, userEmail)

    @DeleteMapping("/deleteDiagram/{diagramName}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun deleteDiagram(@PathVariable diagramName: String, @RequestBody userEmail: String) =
        diagramService.deleteDiagram(diagramName, userEmail)

}