package com.nicoqueijo.android.todolist.domain.usecases

import com.nicoqueijo.android.todolist.data.Repository
import com.nicoqueijo.android.todolist.domain.model.ToDo
import io.kotest.matchers.shouldBe
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class RetrieveToDosUseCaseTest {

    private lateinit var repository: Repository
    private lateinit var subject: RetrieveToDosUseCase

    @BeforeEach
    fun setUp() {
        repository = mockk()
        subject = RetrieveToDosUseCase(repository)
    }

    @Test
    fun `given repository returns a flow of ToDo items, when invoke is called, then the flow is returned correctly`() =
        runTest {
            val expected = flowOf(
                listOf(
                    ToDo(
                        id = 1,
                        position = 0,
                        title = "ToDo 1"
                    ),
                    ToDo(
                        id = 2,
                        position = 1,
                        title = "ToDo 2"
                    )
                )
            )

            coEvery {
                repository.getAllToDos()
            }.returns(expected)

            val actual = subject.invoke()

            actual.shouldBe(expected = expected)
        }
}
