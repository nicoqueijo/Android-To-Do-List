package com.nicoqueijo.android.todolist.domain.usecases

import com.nicoqueijo.android.todolist.data.Repository
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.just
import io.mockk.mockk
import io.mockk.runs
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class DeleteAllToDosUseCaseTest {

    private lateinit var repository: Repository
    private lateinit var subject: DeleteAllToDosUseCase

    @BeforeEach
    fun setUp() {
        repository = mockk()
        subject = DeleteAllToDosUseCase(repository)
    }

    @Test
    fun `given the use case, when invoke is called, then repository deleteAllToDos should be called`() =
        runTest {
            coEvery {
                repository.deleteAllToDos()
            }.just(runs)

            subject.invoke()

            coVerify(exactly = 1) {
                repository.deleteAllToDos()
            }
        }
}
