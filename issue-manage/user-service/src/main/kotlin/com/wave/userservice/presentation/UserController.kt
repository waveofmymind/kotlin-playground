package com.wave.userservice.presentation

import com.wave.userservice.application.UserService
import com.wave.userservice.model.SignInRequest
import com.wave.userservice.model.SignInResponse
import com.wave.userservice.model.SignUpRequest
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1/users")
class UserController(
    private val userService: UserService,
) {

    @PostMapping("/signup")
    suspend fun signUp(@RequestBody signUpRequest: SignUpRequest) {
        userService.signUp(signUpRequest)
    }

    @PostMapping("/signin")
    suspend fun signIn(@RequestBody signInRequest: SignInRequest) = userService.signIn(signInRequest)

}