package br.com.mydiagram.controller

import br.com.mydiagram.controller.request.GetMyDiagramUserRequest
import br.com.mydiagram.controller.request.PostMyDiagramUserRequest
import br.com.mydiagram.controller.request.PutMyDiagramUserRequest
import br.com.mydiagram.controller.response.AuthenticationResponse
import br.com.mydiagram.extensions.toMyDiagramUser
import br.com.mydiagram.service.MyDiagramUserService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.util.*

@RestController
@RequestMapping("/api/v1/mydiagram")
class MyDiagramUserController(val myDiagramUserService: MyDiagramUserService) {

    //TODO Criar um getMyDiagramUser Request
    @GetMapping("/login")
    fun login(@RequestBody getMyDiagramUserRequest: GetMyDiagramUserRequest): ResponseEntity<AuthenticationResponse>{
        return ResponseEntity.ok(myDiagramUserService.login(getMyDiagramUserRequest.email, getMyDiagramUserRequest.pass))
    }

    @PostMapping("/signup")
    @ResponseStatus(HttpStatus.CREATED)
    fun signup(@RequestBody myDiagramUser: PostMyDiagramUserRequest) =
        myDiagramUserService.signup(myDiagramUser.toMyDiagramUser())

    @PutMapping("/change-profile/{email}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun changeProfile(@PathVariable email: String, @RequestBody myDiagramUser: PutMyDiagramUserRequest) =
        myDiagramUserService.changeProfile(myDiagramUser.toMyDiagramUser(email))

    @DeleteMapping("exclude/{email}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun deleteProfile(@PathVariable email: String) =
        myDiagramUserService.deleteProfile(email)

}