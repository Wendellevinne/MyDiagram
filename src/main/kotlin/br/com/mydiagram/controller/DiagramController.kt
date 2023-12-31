package br.com.mydiagram.controller

import br.com.mydiagram.controller.request.diagram.EditDiagramRequest
import br.com.mydiagram.controller.request.diagram.DeleteDiagramRequest
import br.com.mydiagram.controller.request.diagram.GetDiagramRequest
import br.com.mydiagram.controller.request.diagram.PostDiagramRequest
import br.com.mydiagram.model.Diagram
import br.com.mydiagram.security.JwtService
import br.com.mydiagram.service.DiagramService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.multipart.MultipartFile
import java.util.*


@RestController
@RequestMapping("/api/v1/mydiagram/diagrams")
class DiagramController(
    val diagramService: DiagramService,
    val jwtService: JwtService
) {

    @GetMapping("/getDiagrams")
    fun getAllDiagrams(@RequestHeader("Authorization") authorization: String): ResponseEntity<Optional<List<Diagram>?>> {
        if (!authorization.startsWith("Bearer ")){
            throw Exception()
        }
        val userId = jwtService.extractUsername(authorization.substring(7))
        return ResponseEntity.ok(diagramService.getAllDiagrams(userId))
    }

    @GetMapping("/getDiagram")
    fun getDiagram(@RequestParam("path") path: String,
                   @RequestHeader("Authorization") authorization: String): ResponseEntity<Diagram> {
        if (!authorization.startsWith("Bearer ")){
            throw Exception()
        }
        val userId = jwtService.extractUsername(authorization.substring(7))
        val getDiagramRequest = GetDiagramRequest(path, userId)
        return ResponseEntity.ok(diagramService.getDiagram(getDiagramRequest))
    }

    @PostMapping("/createDiagram")
    @ResponseStatus(HttpStatus.CREATED)
    fun createDiagram(@RequestParam("name") name: String,
                      @RequestPart("file") file: MultipartFile,
                      @RequestHeader("Authorization") authorization: String): ResponseEntity<Diagram> {
        if (!authorization.startsWith("Bearer ")){
            throw Exception()
        }
        val userId = jwtService.extractUsername(authorization.substring(7))
        val postDiagramRequest = PostDiagramRequest(name, userId, file)
        return ResponseEntity.ok(diagramService.createDiagram(postDiagramRequest))
    }

    @PutMapping("/editDiagram")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun editDiagram(@RequestParam path: String,
                    @RequestParam newName: String? = null,
                    @RequestPart("file") file: MultipartFile,
                    @RequestHeader("Authorization") authorization: String){
        if (!authorization.startsWith("Bearer ")){
            throw Exception()
        }
        val userId = jwtService.extractUsername(authorization.substring(7))
        val editDiagramRequest = EditDiagramRequest(path, newName, userId, file)
        diagramService.editDiagram(editDiagramRequest)
    }

    @DeleteMapping("/deleteDiagram")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun deleteDiagram(@RequestParam("path") path: String,
                      @RequestHeader("Authorization") authorization: String){
        if (!authorization.startsWith("Bearer ")){
            throw Exception()
        }
        val userId = jwtService.extractUsername(authorization.substring(7))
        val deleteDiagramRequest = DeleteDiagramRequest(path, userId)
        diagramService.deleteDiagram(deleteDiagramRequest)
    }

}