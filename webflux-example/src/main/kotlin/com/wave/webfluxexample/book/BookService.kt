package com.wave.webfluxexample.book

import org.springframework.stereotype.Service
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import reactor.kotlin.core.publisher.toFlux
import java.util.concurrent.atomic.AtomicLong

data class Book(val id: Long, val name: String, val price: Int)

@Service
class BookService {

    private final val nextId = AtomicLong(0)

    val books = mutableListOf(
        Book(nextId.incrementAndGet(), "코틀린 인 액션", 10000),
        Book(nextId.incrementAndGet(), "HTTP 완벽 가이드", 20000)
    )

    fun getAll(): Flux<Book> {
        return books.toFlux()
    }

    fun get(id: Long): Mono<Book> {
        return Mono.justOrEmpty(books.find { it.id == id })
    }

    fun add(request: Map<String, Any>): Mono<Book> {
        return Mono.just(request)
            .map {map ->
                val book = Book(
                    id = nextId.incrementAndGet(),
                    name = map["name"].toString(),
                    price = map["price"] as Int
                )

                books.add(book)
                book
            }
    }

    fun delete(id: Long): Mono<Void> {
        return Mono.justOrEmpty(books.find { it.id == id })
            .map {books.remove(it)}
            .then()
    }
}