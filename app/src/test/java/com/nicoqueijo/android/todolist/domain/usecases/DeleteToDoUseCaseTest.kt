package com.nicoqueijo.android.todolist.domain.usecases

import com.nicoqueijo.android.todolist.data.Repository
import com.nicoqueijo.android.todolist.domain.model.ToDo
import io.mockk.Runs
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.just
import io.mockk.mockk
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class DeleteToDoUseCaseTest {

    private lateinit var repository: Repository
    private lateinit var subject: DeleteToDoUseCase

    @BeforeEach
    fun setUp() {
        repository = mockk()
        subject = DeleteToDoUseCase(repository)
    }

    @Test
    fun `given a ToDo item to delete, when invoke is called, then repository methods should be called with correct arguments`() =
        runTest {
            val toDo = ToDo(
                id = 2,
                position = 1,
                title = "ToDo 2"
            )
            val toDos = listOf(
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
            coEvery {
                repository.getAllToDos()
            }.returns(flowOf(toDos))
            coEvery {
                repository.upsertToDos(any())
            }.just(Runs)
            coEvery {
                repository.deleteToDo(any())
            }.just(Runs)

            subject.invoke(toDo)

            coVerify(exactly = 1) {
                repository.getAllToDos()
            }
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
                            position = 1,
                            title = "ToDo 3"
                        )
                    )
                )
            }
            coVerify(exactly = 1) {
                repository.deleteToDo(toDo)
            }
        }
}