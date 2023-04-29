package com.wave.todo.dto

data class TodoRequest(
    val title: String,
    val description: String,
    val done: Boolean = false,
)
