package com.wave.todo.dto

import com.fasterxml.jackson.annotation.JsonIgnore
import com.wave.todo.domain.Todo

data class TodoListResponse(
    val items: List<TodoResponse>,

    ) {
    val size: Int
        @JsonIgnore
        get() = items.size

    fun get(index: Int) = items[index]

    companion object {
        fun of(todoList: List<Todo>) =
            TodoListResponse(todoList.map(TodoResponse::of))
    }
}

