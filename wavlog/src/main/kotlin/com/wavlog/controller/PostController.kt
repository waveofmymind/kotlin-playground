package com.wavlog.controller

import com.wavlog.dto.request.PostCreate
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class PostController {



    @PostMapping("/posts")
    fun post(@RequestBody request : PostCreate) : String {
        println(request)
        return "Hello World"
    }
}