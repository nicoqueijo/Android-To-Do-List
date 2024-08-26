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

class ToggleCompleteToDoUseCaseTest {

    private lateinit var repository: Repository
    private lateinit var subject: ToggleCompleteToDoUseCase

    @BeforeEach
    fun setUp() {
        repository = mockk()
        subject = ToggleCompleteToDoUseCase(repository)
    }

    @Test
    fun `given a completed ToDo item, when invoke is called, then the ToDo's completion status is toggled and upserted`() =
        runTest {
            val toDo = ToDo(
                title = "Sample ToDo",
                isCompleted = true
            )

            coEvery {
                repository.upsertToDo(any())
            }.just(Runs)

            subject.invoke(toDo)

            coVerify(exactly = 1) {
                repository.upsertToDo(
                    toDo = toDo.copy(isCompleted = false)
                )
            }
        }

    @Test
    fun `given an uncompleted ToDo item, when invoke is called, then the ToDo's completion status is toggled and upserted`() =
        runTest {
            val toDo = ToDo(
                title = "Sample ToDo",
                isCompleted = false
            )

            coEvery {
                repository.upsertToDo(any())
            }.just(Runs)

            subject.invoke(toDo)

            coVerify(exactly = 1) {
                repository.upsertToDo(
                    toDo = toDo.copy(isCompleted = true)
                )
            }
        }
}
