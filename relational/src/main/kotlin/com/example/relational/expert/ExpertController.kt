package com.example.relational.expert

import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class ExpertController(
    val expertService: ExpertService
) {

    @PostMapping("/experts")
    fun registerExpert(@RequestBody request : ExpertRegister) {
        expertService.registerExpert(request)
    }
}