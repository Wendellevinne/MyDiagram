package br.com.mydiagram.controller

import br.com.mydiagram.controller.request.user.GetMyDiagramUserRequest
import br.com.mydiagram.controller.request.user.PostMyDiagramUserRequest
import br.com.mydiagram.controller.request.user.PutMyDiagramUserRequest
import br.com.mydiagram.controller.response.AuthenticationResponse
import br.com.mydiagram.extensions.toMyDiagramUser
import br.com.mydiagram.model.MyDiagramUser
import br.com.mydiagram.security.JwtService
import br.com.mydiagram.service.MyDiagramUserService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.util.*

@RestController
@RequestMapping("/api/v1/mydiagram/users")
class MyDiagramUserController(
    val myDiagramUserService: MyDiagramUserService,
    val jwtService: JwtService
) {

    @GetMapping("/me")
    fun getProfile(@RequestHeader("Authorization") authorization: String): ResponseEntity<Optional<MyDiagramUser?>> {
        if (!authorization.startsWith("Bearer ")){
            throw Exception()
        }
        val email = jwtService.extractUsername(authorization.substring(7))
        return ResponseEntity.ok(myDiagramUserService.getProfile(email))
    }

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