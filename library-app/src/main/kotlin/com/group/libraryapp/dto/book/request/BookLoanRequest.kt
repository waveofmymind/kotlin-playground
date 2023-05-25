package com.group.libraryapp.dto.book.request

data class BookLoanRequest(
    val username : String,
    val bookName: String,
)