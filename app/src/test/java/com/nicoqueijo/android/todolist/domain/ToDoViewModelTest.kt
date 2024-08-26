package com.nicoqueijo.android.todolist.domain

import com.nicoqueijo.android.todolist.domain.model.ToDo
import com.nicoqueijo.android.todolist.domain.model.UiEvent
import com.nicoqueijo.android.todolist.domain.usecases.ToDoUseCases
import com.nicoqueijo.android.todolist.presentation.model.UiState
import io.kotest.matchers.shouldBe
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.just
import io.mockk.mockk
import io.mockk.runs
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

@OptIn(ExperimentalCoroutinesApi::class)
class ToDoViewModelTest {

    private lateinit var useCases: ToDoUseCases
    private lateinit var dispatcher: CoroutineDispatcher
    private lateinit var subject: ToDoViewModel

    @BeforeEach
    fun setUp() {
        useCases = mockk(relaxed = true)
        dispatcher = UnconfinedTestDispatcher()
        subject = ToDoViewModel(
            useCases = useCases,
            dispatcher = dispatcher
        )
    }

    @Nested
    inner class OnEvent {

        @Test
        fun `given AddToDo event, when onEvent is called, then update UI state to show bottom sheet`() =
            runTest {
                val uiState = UiState()

                subject.onEvent(UiEvent.AddToDo)

                subject.uiState.value.shouldBe(
                    uiState.copy(
                        activeToDo = null,
                        showBottomSheet = true,
                        isFirstLaunch = false,
                    )
                )
            }

        @Test
        fun `given EditToDo event, when onEvent is called, then update UI state with active ToDo and show bottom sheet`() =
            runTest {
                val uiState = UiState()
                val toDo = ToDo(
                    title = "Grocery Shopping",
                    description = "Buy groceries for the week, including fruits, vegetables, milk, bread, and eggs.",
                    isCompleted = true
                )

                subject.onEvent(
                    UiEvent.EditToDo(toDo = toDo)
                )

                subject.uiState.value.shouldBe(
                    uiState.copy(
                        activeToDo = toDo,
                        showBottomSheet = true,
                        isFirstLaunch = false,
                    )
                )
            }

        @Test
        fun `given SaveToDo event, when onEvent is called, then save ToDo use case is invoked and UI state is updated`() =
            runTest {
                val uiState = UiState()
                val toDo = ToDo(
                    title = "Grocery Shopping",
                    description = "Buy groceries for the week, including fruits, vegetables, milk, bread, and eggs.",
                    isCompleted = true
                )
                coEvery {
                    useCases.saveToDoUseCase(toDo = toDo)
                }.just(runs)

                subject.onEvent(
                    UiEvent.SaveToDo(toDo = toDo)
                )

                coVerify(exactly = 1) {
                    useCases.saveToDoUseCase(toDo = toDo)
                }
                subject.uiState.value.shouldBe(
                    uiState.copy(
                        activeToDo = null,
                        showBottomSheet = false,
                        isFirstLaunch = false,
                    )
                )
            }

        @Test
        fun `given DismissBottomSheet event, when onEvent is called, then update UI state to hide bottom sheet`() =
            runTest {
                val uiState = UiState()

                subject.onEvent(UiEvent.DismissBottomSheet)

                subject.uiState.value.shouldBe(
                    uiState.copy(
                        activeToDo = null,
                        showBottomSheet = false,
                        isFirstLaunch = false,
                    )
                )
            }

        @Test
        fun `given ToggleCompleteToDo event, when onEvent is called, then toggle complete ToDo use case is invoked`() =
            runTest {
                val toDo = ToDo(
                    title = "Grocery Shopping",
                    description = "Buy groceries for the week, including fruits, vegetables, milk, bread, and eggs.",
                    isCompleted = true
                )
                coEvery {
                    useCases.toggleCompleteToDoUseCase(toDo = toDo)
                }.just(runs)

                subject.onEvent(
                    UiEvent.ToggleCompleteToDo(toDo = toDo)
                )

                coVerify(exactly = 1) {
                    useCases.toggleCompleteToDoUseCase(toDo = toDo)
                }
            }

        @Test
        fun `given DeleteAllToDos event, when onEvent is called, then update UI to hide the dialog`() =
            runTest {
                val uiState = UiState()

                subject.onEvent(UiEvent.DeleteAllToDos)

                subject.uiState.value.shouldBe(
                    uiState.copy(
                        showDialog = true,
                        isFirstLaunch = false,
                    )
                )
            }

        @Test
        fun `given DeleteToDo event, when onEvent is called, then delete ToDo use case is invoked`() =
            runTest {
                val toDo = ToDo(
                    title = "Grocery Shopping",
                    description = "Buy groceries for the week, including fruits, vegetables, milk, bread, and eggs.",
                    isCompleted = true
                )
                coEvery {
                    useCases.deleteToDoUseCase(toDo = toDo)
                }.just(runs)

                subject.onEvent(
                    UiEvent.DeleteToDo(toDo = toDo)
                )

                coVerify(exactly = 1) {
                    useCases.deleteToDoUseCase(toDo = toDo)
                }
            }

        @Test
        fun `given ReorderToDos event, when onEvent is called, then reorder ToDos use case is invoked`() =
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
                    useCases.reorderToDosUseCase(toDos = toDos)
                }.just(runs)

                subject.onEvent(
                    UiEvent.ReorderToDos(toDos = toDos)
                )

                coVerify(exactly = 1) {
                    useCases.reorderToDosUseCase(toDos = toDos)
                }
            }

        @Test
        fun `given RestoreToDo event, when onEvent is called, then restore ToDo use case is invoked`() =
            runTest {
                val toDo = ToDo(
                    title = "Grocery Shopping",
                    description = "Buy groceries for the week, including fruits, vegetables, milk, bread, and eggs.",
                )
                coEvery {
                    useCases.restoreToDoUseCase(toDo = toDo)
                }.just(runs)

                subject.onEvent(
                    UiEvent.RestoreToDo(toDo = toDo)
                )

                coVerify(exactly = 1) {
                    useCases.restoreToDoUseCase(toDo = toDo)
                }
            }

        @Test
        fun `given ConfirmDialog event, when onEvent is called, then delete all ToDos use case is invoked and dialog is hidden`() =
            runTest {
                val uiState = UiState()
                coEvery {
                    useCases.deleteAllToDosUseCase()
                }.just(runs)

                subject.onEvent(
                    UiEvent.ConfirmDialog
                )

                coVerify(exactly = 1) {
                    useCases.deleteAllToDosUseCase()
                }
                subject.uiState.value.shouldBe(
                    uiState.copy(
                        showDialog = false,
                        isFirstLaunch = false,
                    )
                )
            }

        @Test
        fun `given CancelDialog event, when onEvent is called, then dialog is hidden`() =
            runTest {
                val uiState = UiState()

                subject.onEvent(
                    UiEvent.CancelDialog
                )

                subject.uiState.value.shouldBe(
                    uiState.copy(
                        showDialog = false,
                        isFirstLaunch = false,
                    )
                )
            }

        @Test
        fun `given ToggleOffIsFirstLaunch event, when onEvent is called, then toggle off IsFirstLaunch use case is invoked and IsFirstLaunch is set to false`() =
            runTest {
                val uiState = UiState()
                coEvery {
                    useCases.toggleOffIsFirstLaunchUseCase()
                }.just(runs)

                subject.onEvent(
                    UiEvent.ToggleOffIsFirstLaunch
                )

                coVerify(exactly = 1) {
                    useCases.toggleOffIsFirstLaunchUseCase()
                }
                subject.uiState.value.shouldBe(
                    uiState.copy(
                        isFirstLaunch = false,
                    )
                )
            }
    }
}
