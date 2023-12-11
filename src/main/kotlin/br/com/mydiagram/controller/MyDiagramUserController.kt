package br.com.mydiagram.controller

import br.com.mydiagram.controller.request.user.GetMyDiagramUserRequest
import br.com.mydiagram.controller.request.user.GetMyDiagramUserWithoutPassRequest
import br.com.mydiagram.controller.request.user.PostMyDiagramUserRequest
import br.com.mydiagram.controller.request.user.PutMyDiagramUserRequest
import br.com.mydiagram.controller.response.AuthenticationResponse
import br.com.mydiagram.extensions.toMyDiagramUser
import br.com.mydiagram.service.MyDiagramUserService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/v1/mydiagram/users")
class MyDiagramUserController(val myDiagramUserService: MyDiagramUserService) {

    @GetMapping("/me")
    fun getProfile(@RequestBody getMyDiagramUserWithoutPassRequest: GetMyDiagramUserWithoutPassRequest) =
        ResponseEntity.ok(myDiagramUserService.getProfile(getMyDiagramUserWithoutPassRequest.email))

    @PostMapping("/login")
    fun login(@RequestBody getMyDiagramUserRequest: GetMyDiagramUserRequest): ResponseEntity<AuthenticationResponse> =
        ResponseEntity.ok(myDiagramUserService.login(getMyDiagramUserRequest.email, getMyDiagramUserRequest.pass))

    @PostMapping("/signup")
    @ResponseStatus(HttpStatus.CREATED)
    fun signup(@RequestBody myDiagramUser: PostMyDiagramUserRequest) =
        myDiagramUserService.signup(myDiagramUser.toMyDiagramUser())

    @PutMapping("/change-profile/{email}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun changeProfile(@PathVariable email: String, @RequestBody myDiagramUser: PutMyDiagramUserRequest) =
        myDiagramUserService.changeProfile(myDiagramUser.toMyDiagramUser(email))

    @DeleteMapping("/exclude/{email}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun deleteProfile(@PathVariable email: String) =
        myDiagramUserService.deleteProfile(email)

}