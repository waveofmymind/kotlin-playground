package com.wavlog.controller

import com.wavlog.dto.request.PostCreate
import jakarta.validation.Valid
import org.springframework.context.annotation.Scope
import org.springframework.validation.BindingResult
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
@Scope("prototype")
class PostController {



    @PostMapping("/posts")
    fun post(@Valid @RequestBody request : PostCreate, result : BindingResult) : Map<String, String> {
        if (result.hasErrors()) {
            val fieldErrors =   result.fieldErrors
            val firstFieldError = fieldErrors.get(0)
            val fieldName = firstFieldError.field
            val message = firstFieldError.defaultMessage

            val error = HashMap<String, String>()
            error[fieldName] = message!!
            return error
        }
        return HashMap<String, String>()
    }
}