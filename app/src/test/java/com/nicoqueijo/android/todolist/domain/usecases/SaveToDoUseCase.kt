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

class SaveToDoUseCaseTest {

    private lateinit var repository: Repository
    private lateinit var subject: SaveToDoUseCase

    @BeforeEach
    fun setUp() {
        repository = mockk()
        subject = SaveToDoUseCase(repository)
    }

    @Test
    fun `given a ToDo item with a specified position, when invoke is called, then upsertToDo should be called with the same position`() =
        runTest {
            val toDo = ToDo(
                position = 1,
                title = "Test ToDo"
            )
            coEvery {
                repository.upsertToDo(toDo = toDo)
            } just (Runs)

            subject.invoke(toDo)

            coVerify(exactly = 1) {
                repository.upsertToDo(
                    toDo = toDo
                )
            }
        }

    @Test
    fun `given a ToDo item with an unspecified position, when invoke is called, then getToDosCount and upsertToDo should be called`() =
        runTest {
            val toDo = ToDo(
                id = 1,
                position = -1,
                title = "Test ToDo"
            )
            coEvery {
                repository.getToDosCount()
            }.returns(3)
            coEvery {
                repository.upsertToDo(any())
            }.just(Runs)

            subject.invoke(toDo)

            coVerify(exactly = 1) {
                repository.getToDosCount()
            }
            coVerify(exactly = 1) {
                repository.upsertToDo(
                    toDo.copy(position = 3)
                )
            }
        }
}
