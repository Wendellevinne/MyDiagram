package br.com.mydiagram.controller

import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController


@RestController
@RequestMapping("/api/v1/mydiagram/diagrams")
class GalleryController() {

    @GetMapping("/getDiagrams")
    fun getAllDiagrams(){
        //TODO Implementar lógica da galeria
    }

    @PostMapping
    fun createDiagram(){
        //TODO Implementar lógica da galeria
    }

    @PutMapping("/editDiagram")
    fun editDiagram(){
        //TODO Implementar lógica da galeria
    }

    @DeleteMapping("/deleteDiagram")
    fun deleteDiagram(){
        //TODO Implementar lógica da galeria
    }

}