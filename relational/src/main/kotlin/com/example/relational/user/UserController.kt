package com.example.relational.user

import org.springframework.web.bind.annotation.*

@RestController
class UserController(
    val userService: UserService
) {

    @PostMapping("/users")
    fun registerUser(@RequestBody request: UserRegister) {
        userService.registerUser(request)
    }

    @GetMapping("/users/{id}")
    fun getUser(@PathVariable id: Long): UserResponse {
        val user = userService.getUser(id)
        return UserResponse(
            id = user.id!!,
            username = user.username,
        )
    }
}
