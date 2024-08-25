package com.nicoqueijo.android.todolist.domain

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nicoqueijo.android.todolist.core.di.DefaultDispatcher
import com.nicoqueijo.android.todolist.domain.model.ToDo
import com.nicoqueijo.android.todolist.domain.model.UiEvent
import com.nicoqueijo.android.todolist.domain.usecases.ToDoUseCases
import com.nicoqueijo.android.todolist.presentation.model.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ToDoViewModel @Inject constructor(
    private val useCases: ToDoUseCases,
    @DefaultDispatcher private val dispatcher: CoroutineDispatcher,
) : ViewModel() {

    private val _uiState = MutableStateFlow(value = UiState())
    val uiState = _uiState.asStateFlow()

    init {
        /*setIsFirstLaunch()*/
        /*setSampleToDos()*/
        viewModelScope.launch(context = dispatcher) {
            val toDos = useCases.retrieveToDosUseCase()
            toDos.collectLatest { databaseToDos ->
                updateToDos(databaseToDos)
            }
        }
    }

    private fun updateToDos(toDos: List<ToDo>) {
        _uiState.value = _uiState.value.copy(
            toDos = toDos
        )
    }

    private fun updateDialogDisplay(toggle: Boolean) {
        _uiState.value = _uiState.value.copy(
            showDialog = toggle
        )
    }

    private fun deleteAllToDos() {
        viewModelScope.launch(context = dispatcher) {
            useCases.deleteAllToDosUseCase()
        }
    }

    private fun deleteCurrency(toDo: ToDo) {
        viewModelScope.launch(context = dispatcher) {
            useCases.deleteToDoUseCase(toDo = toDo)
        }
    }

    /**
     * Handles various UI events and delegates them to the appropriate use cases or functions.
     *
     * @param event The UI event to handle.
     */
    fun onEvent(event: UiEvent) {
        when (event) {
            UiEvent.AddToDo -> {
                // Move logic to private function & use case
                _uiState.value = _uiState.value.copy(
                    activeToDo = null,
                    showBottomSheet = true,
                )
            }

            UiEvent.CancelDialog -> {
                updateDialogDisplay(toggle = false)
            }

            is UiEvent.ToggleCompleteToDo -> {
                viewModelScope.launch(context = dispatcher) {
                    useCases.completeToDosUseCase(toDo = event.toDo)
                }
            }

            UiEvent.ConfirmDialog -> {
                deleteAllToDos()
                updateDialogDisplay(toggle = false)
            }

            UiEvent.DeleteAllToDos -> {
                updateDialogDisplay(toggle = true)
            }

            is UiEvent.DeleteToDo -> {
                deleteCurrency(toDo = event.toDo)
            }

            UiEvent.DismissBottomSheet -> {
                // Move logic to private function & use case
                _uiState.value = _uiState.value.copy(
                    activeToDo = null,
                    showBottomSheet = false,
                )
            }

            is UiEvent.EditToDo -> {
                val toDo = event.toDo

                _uiState.value = _uiState.value.copy(
                    activeToDo = toDo,
                    showBottomSheet = true,
                )
            }

            UiEvent.ReorderToDos -> TODO()
            is UiEvent.RestoreToDo -> {
                restoreToDo(toDo = event.toDo)
            }

            UiEvent.ToggleOffIsFirstLaunch -> TODO()
            is UiEvent.SaveToDo -> {
                viewModelScope.launch(context = dispatcher) {
                    useCases.saveToDoUseCase(toDo = event.toDo)
                }
                _uiState.value = _uiState.value.copy(
                    activeToDo = null,
                    showBottomSheet = false,
                )
            }
        }
    }

    private fun restoreToDo(toDo: ToDo) {
        viewModelScope.launch(context = dispatcher) {
            useCases.restoreToDoUseCase(toDo = toDo)
        }
    }
}