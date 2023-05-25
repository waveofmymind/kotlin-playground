package com.group.libraryapp

import com.group.libraryapp.domain.book.Book
import org.springframework.data.jpa.repository.JpaRepository

interface BookRepository : JpaRepository<Book,Long> {

}