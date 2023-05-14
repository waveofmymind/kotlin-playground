package com.example.relational.user

import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
class UserService(
    val userRepository: UserRepository
) {

    fun registerUser(request: UserRegister) {
        val user = User(
            username = request.username,
        )
        userRepository.save(user)
    }

    fun getUser(id: Long): User {
        val findUser = userRepository.findByIdOrNull(id) ?: throw Exception("User not found")
        println(findUser.username)
        return findUser

    }
}
