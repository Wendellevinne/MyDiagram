package br.com.mydiagram.controller

import br.com.mydiagram.controller.request.GetMyDiagramUserRequest
import br.com.mydiagram.controller.request.GetMyDiagramUserWithoutPassRequest
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
@RequestMapping("/api/v1/mydiagram/users")
class MyDiagramUserController(val myDiagramUserService: MyDiagramUserService) {

    @GetMapping("/login")
    fun login(@RequestBody getMyDiagramUserRequest: GetMyDiagramUserRequest): ResponseEntity<AuthenticationResponse> =
        ResponseEntity.ok(myDiagramUserService.login(getMyDiagramUserRequest.email, getMyDiagramUserRequest.pass))


    @PostMapping("/signup")
    @ResponseStatus(HttpStatus.CREATED)
    fun signup(@RequestBody myDiagramUser: PostMyDiagramUserRequest) =
        myDiagramUserService.signup(myDiagramUser.toMyDiagramUser())

    @GetMapping("/me")
    fun getProfile(@RequestBody getMyDiagramUserWithoutPassRequest: GetMyDiagramUserWithoutPassRequest) =
        ResponseEntity.ok(myDiagramUserService.getProfile(getMyDiagramUserWithoutPassRequest.email))

    @PutMapping("/change-profile/{email}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun changeProfile(@PathVariable email: String, @RequestBody myDiagramUser: PutMyDiagramUserRequest) =
        myDiagramUserService.changeProfile(myDiagramUser.toMyDiagramUser(email))

    @DeleteMapping("exclude/{email}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun deleteProfile(@PathVariable email: String) =
        myDiagramUserService.deleteProfile(email)

}