package com.nicoqueijo.android.todolist.domain.usecases

import com.nicoqueijo.android.todolist.data.Repository
import com.nicoqueijo.android.todolist.domain.model.ToDo
import io.mockk.Runs
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.just
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class ReorderToDosUseCaseTest {

    private lateinit var repository: Repository
    private lateinit var subject: ReorderToDosUseCase

    @BeforeEach
    fun setUp() {
        repository = mockk()
        subject = ReorderToDosUseCase(repository)
    }

    @Test
    fun `given a list of ToDo items, when invoke is called, then repository upsertToDos should be called with reordered list`() =
        runTest {
            val toDoList = listOf(
                ToDo(
                    id = 1,
                    position = 2,
                    title = "ToDo 1"
                ),
                ToDo(
                    id = 2,
                    position = 0,
                    title = "ToDo 2"
                ),
                ToDo(
                    id = 3,
                    position = 1,
                    title = "ToDo 3"
                )
            )

            coEvery {
                repository.upsertToDos(
                    toDos = any()
                )
            }.just(Runs)

            subject.invoke(toDoList)

            coVerify(exactly = 1) {
                repository.upsertToDos(
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
                        ),
                        ToDo(
                            id = 3,
                            position = 2,
                            title = "ToDo 3"
                        )
                    )
                )
            }
        }
}
