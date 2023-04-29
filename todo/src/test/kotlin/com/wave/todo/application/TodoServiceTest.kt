package com.wave.todo.application

import com.ninjasquad.springmockk.MockkBean
import com.wave.todo.applicetion.TodoService
import com.wave.todo.domain.Todo
import com.wave.todo.domain.TodoRepository
import io.mockk.every
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.data.repository.findByIdOrNull
import org.springframework.test.context.junit.jupiter.SpringExtension
import java.time.LocalDateTime

@ExtendWith(SpringExtension::class)
class TodoServiceTest {

    @MockkBean
    lateinit var todoRepository: TodoRepository

    lateinit var todoService: TodoService

    val stub : Todo by lazy {
        Todo(
            id = 1L,
            title = "title",
            description = "description",
            done = false,
            createdAt = LocalDateTime.now(),
            updatedAt = LocalDateTime.now()
        )
    }

    @BeforeEach
    fun setUp() {
        todoService = TodoService(todoRepository)
    }

    @Test
    fun `한개의 TODO를 반환한다`() {
        // given
        every { todoRepository.findByIdOrNull(1) } returns stub
        // when
        val actual = todoService.findById(1)

        // then
        assertThat(actual).isNotNull
        assertThat(actual).isEqualTo(stub)

    }
}