package com.example.relational.expert

import com.example.relational.user.User
import org.springframework.stereotype.Service

@Service
class ExpertService(
    val expertRepository: ExpertRepository
) {

    fun registerExpert(request: ExpertRegister) {
        val expert = Expert(
            career = request.career,
            user = User(
                username = "test",
            )
        )
        expertRepository.save(expert)
    }
}