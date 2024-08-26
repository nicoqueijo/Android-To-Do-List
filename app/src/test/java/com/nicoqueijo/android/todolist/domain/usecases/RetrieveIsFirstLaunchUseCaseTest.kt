package com.nicoqueijo.android.todolist.domain.usecases

import com.nicoqueijo.android.todolist.data.Repository
import io.kotest.matchers.shouldBe
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

class RetrieveIsFirstLaunchUseCaseTest {

    private lateinit var repository: Repository
    private lateinit var subject: RetrieveIsFirstLaunchUseCase

    @BeforeEach
    fun setUp() {
        repository = mockk()
        subject = RetrieveIsFirstLaunchUseCase(repository)
    }

    @ParameterizedTest
    @ValueSource(booleans = [true, false])
    fun `given repository returns a boolean, when invoke is called, then result should match repository output`(
        isFirstLaunch: Boolean
    ) = runTest {
        coEvery {
            repository.isFirstLaunch()
        } returns (isFirstLaunch)

        val actual = subject.invoke()

        actual.shouldBe(expected = isFirstLaunch)
    }
}
