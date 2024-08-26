package com.nicoqueijo.android.todolist.domain.usecases

import com.nicoqueijo.android.todolist.data.Repository
import com.nicoqueijo.android.todolist.domain.model.ToDo
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.just
import io.mockk.mockk
import io.mockk.runs
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class SetSampleToDosUseCaseTest {

    private lateinit var repository: Repository
    private lateinit var subject: SetSampleToDosUseCase

    @BeforeEach
    fun setUp() {
        repository = mockk()
        subject = SetSampleToDosUseCase(repository)
    }

    @Test
    fun `given first launch is true, when invoke is called, then sample ToDos are inserted into repository`() =
        runTest {
            val sampleToDos = listOf(
                ToDo(
                    position = 0,
                    title = "Grocery Shopping",
                    description = "Buy groceries for the week, including fruits, vegetables, milk, bread, and eggs.",
                ),
                ToDo(
                    position = 1,
                    title = "Plan Weekend Trip",
                    description = "Research destinations for a weekend trip, book a hotel, and plan activities.",
                ),
                ToDo(
                    position = 2,
                    title = "Morning Exercise",
                    description = "Go for a 30-minute run in the park and complete a 15-minute stretching session.",
                ),
            )
            coEvery {
                repository.isFirstLaunch()
            }.returns(true)
            coEvery {
                repository.upsertToDos(toDos = sampleToDos)
            }.just(runs)

            subject.invoke()

            coVerify(exactly = 1) {
                repository.isFirstLaunch()
            }
            coVerify(exactly = 1) {
                repository.upsertToDos(toDos = sampleToDos)
            }
        }

    @Test
    fun `given first launch is false, when invoke is called, then no ToDos are inserted into repository`() =
        runTest {
            coEvery {
                repository.isFirstLaunch()
            }.returns(false)

            subject.invoke()

            coVerify(exactly = 1) {
                repository.isFirstLaunch()
            }
            coVerify(exactly = 0) {
                repository.upsertToDos(any())
            }
        }
}
