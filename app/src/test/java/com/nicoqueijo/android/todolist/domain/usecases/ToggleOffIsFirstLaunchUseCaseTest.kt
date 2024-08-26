package com.nicoqueijo.android.todolist.domain.usecases

import com.nicoqueijo.android.todolist.data.Repository
import io.mockk.Runs
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.just
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class ToggleOffIsFirstLaunchUseCaseTest {

    private lateinit var repository: Repository
    private lateinit var subject: ToggleOffIsFirstLaunchUseCase

    @BeforeEach
    fun setUp() {
        repository = mockk()
        subject = ToggleOffIsFirstLaunchUseCase(repository)
    }

    @Test
    fun `given the use case, when invoke is called, then repository setFirstLaunch should be called with the correct value`() =
        runTest {
            coEvery {
                repository.setFirstLaunch(any())
            }.just(Runs)

            subject.invoke()

            coVerify(exactly = 1) {
                repository.setFirstLaunch(value = false)
            }
        }
}
