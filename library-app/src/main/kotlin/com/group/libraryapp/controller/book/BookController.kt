package com.group.libraryapp.controller.book

import com.group.libraryapp.dto.book.request.BookLoanRequest
import com.group.libraryapp.dto.book.request.BookRequest
import com.group.libraryapp.dto.book.request.BookReturnRequest
import com.group.libraryapp.service.BookService
import com.group.libraryapp.service.UserService
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class BookController(
    private val bookService: BookService,
    private val userService: UserService
) {

    @PostMapping("/book")
    fun saveBook(@RequestBody request: BookRequest) {
        bookService.saveBook(request)
    }

    @PostMapping("/book/loan")
    fun loanBOok(@RequestBody request: BookLoanRequest) {
        bookService.loanBook(request)
    }

    @PutMapping("/book/return")
    fun returnBook(@RequestBody request : BookReturnRequest) {
        bookService.returnBook(request)
    }
}