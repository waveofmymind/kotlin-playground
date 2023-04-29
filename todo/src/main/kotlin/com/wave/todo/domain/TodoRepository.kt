package com.wave.todo.domain

import org.springframework.data.jpa.repository.JpaRepository

interface TodoRepository : JpaRepository<Todo, Long> {

    fun findAllByDoneIsFalseOrderByIdDesc(): List<Todo>?
}