package com.nicoqueijo.android.todolist.data

import com.nicoqueijo.android.todolist.domain.model.ToDo
import io.kotest.matchers.shouldBe
import io.mockk.Runs
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.just
import io.mockk.mockk
import io.mockk.runs
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

@OptIn(ExperimentalCoroutinesApi::class)
class ToDoRepositoryTest {

    private lateinit var toDoDao: ToDoDao
    private lateinit var dataStoreManager: DataStoreManager
    private lateinit var dispatcher: CoroutineDispatcher
    private lateinit var subject: ToDoRepository

    @BeforeEach
    fun setUp() {
        toDoDao = mockk()
        dataStoreManager = mockk()
        dispatcher = UnconfinedTestDispatcher()
        subject = ToDoRepository(
            toDoDao = toDoDao,
            dataStoreManager = dataStoreManager,
            dispatcher = dispatcher
        )
    }

    @Nested
    inner class UpsertToDo {

        @Test
        fun `given a ToDo item, when upsertToDo is called, then dao upsertToDo is invoked`() =
            runTest {
                val toDo = ToDo(
                    title = "Grocery Shopping",
                    description = "Buy groceries for the week, including fruits, vegetables, milk, bread, and eggs.",
                )
                coEvery {
                    toDoDao.upsertToDo(toDo = toDo)
                }.just(runs)

                subject.upsertToDo(toDo = toDo)

                coVerify(exactly = 1) {
                    toDoDao.upsertToDo(toDo = toDo)
                }
            }
    }

    @Nested
    inner class UpsertToDos {

        @Test
        fun `given a list of ToDos, when upsertToDos is called, then dao upsertToDos is invoked`() =
            runTest {
                val toDos = listOf(
                    ToDo(
                        title = "Grocery Shopping",
                        description = "Buy groceries for the week, including fruits, vegetables, milk, bread, and eggs.",
                    ),
                    ToDo(
                        title = "Plan Weekend Trip",
                        description = "Research destinations for a weekend trip, book a hotel, and plan activities.",
                    )
                )
                coEvery {
                    toDoDao.upsertToDos(toDos = toDos)
                }.just(runs)

                subject.upsertToDos(toDos = toDos)

                coVerify(exactly = 1) {
                    toDoDao.upsertToDos(toDos = toDos)
                }
            }
    }

    @Nested
    inner class DeleteToDo {

        @Test
        fun `given a ToDo item, when deleteToDo is called, then dao deleteToDo is invoked`() =
            runTest {
                val toDo = ToDo(
                    title = "Grocery Shopping",
                    description = "Buy groceries for the week, including fruits, vegetables, milk, bread, and eggs.",
                )
                coEvery {
                    toDoDao.deleteToDo(toDo = toDo)
                }.just(runs)

                subject.deleteToDo(toDo = toDo)

                coVerify(exactly = 1) {
                    toDoDao.deleteToDo(toDo = toDo)
                }
            }
    }

    @Nested
    inner class DeleteAllToDos {

        @Test
        fun `given deleteAllToDos is called, when it is invoked, then dao deleteAllToDos is invoked`() =
            runTest {
                coEvery {
                    toDoDao.deleteAllToDos()
                }.just(runs)

                subject.deleteAllToDos()

                coVerify(exactly = 1) {
                    toDoDao.deleteAllToDos()
                }
            }
    }

    @Nested
    inner class GetAllToDos {

        @Test
        fun `given getAllToDos is called, when it is invoked, then dao getAllToDos is invoked and correct flow is returned`() =
            runTest {
                val expected = flowOf(
                    listOf(
                        ToDo(
                            title = "Grocery Shopping",
                            description = "Buy groceries for the week, including fruits, vegetables, milk, bread, and eggs.",
                        ),
                        ToDo(
                            title = "Plan Weekend Trip",
                            description = "Research destinations for a weekend trip, book a hotel, and plan activities.",
                        )
                    )
                )
                coEvery {
                    toDoDao.getAllToDos()
                } returns expected

                val actual = subject.getAllToDos()

                actual.shouldBe(expected = expected)
            }
    }

    @Nested
    inner class GetToDosCount {

        @Test
        fun `given getToDosCount is called, when it is invoked, then dao getToDosCount is invoked and correct count is returned`() =
            runTest {
                val expected = 2
                coEvery {
                    toDoDao.getToDosCount()
                } returns expected

                val actual = subject.getToDosCount()

                actual.shouldBe(expected = expected)
            }
    }

    @Nested
    inner class SetFirstLaunch {

        @ParameterizedTest
        @ValueSource(booleans = [true, false])
        fun `given boolean value, when setFirstLaunch is called, then correct value is passed to dataStoreManager`(
            value: Boolean
        ) = runTest {
            coEvery {
                dataStoreManager.setFirstLaunch(
                    value = value
                )
            }.just(Runs)

            subject.setFirstLaunch(value = value)

            coVerify(exactly = 1) {
                dataStoreManager.setFirstLaunch(
                    value = value
                )
            }
        }
    }

    @Nested
    inner class IsFirstLaunch {

        @ParameterizedTest
        @ValueSource(booleans = [true, false])
        fun `given boolean return value, when isFirstLaunch is called, then correct value is returned`(
            expected: Boolean
        ) = runTest {
            coEvery {
                dataStoreManager.isFirstLaunch()
            }.returns(expected)

            val actual = subject.isFirstLaunch()

            actual.shouldBe(expected = expected)
        }
    }
}