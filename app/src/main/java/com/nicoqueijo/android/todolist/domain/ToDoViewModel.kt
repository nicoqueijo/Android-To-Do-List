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

/**
 * ViewModel for managing and interacting with To-Do data and UI state.
 *
 * @property useCases The [ToDoUseCases] instance containing use cases for To-Do operations.
 * @property dispatcher The [CoroutineDispatcher] for handling coroutines.
 */
@HiltViewModel
class ToDoViewModel @Inject constructor(
    private val useCases: ToDoUseCases,
    @DefaultDispatcher private val dispatcher: CoroutineDispatcher,
) : ViewModel() {

    private val _uiState = MutableStateFlow(UiState())
    val uiState = _uiState.asStateFlow()

    init {
        viewModelScope.launch(context = dispatcher) {
            setIsFirstLaunch()
            setSampleToDos()
            val toDos = useCases.retrieveToDosUseCase()
            toDos.collectLatest { databaseToDos ->
                updateToDos(databaseToDos)
            }
        }
    }

    /**
     * Handles various UI events and delegates them to the appropriate use cases or functions.
     *
     * @param event The [UiEvent] to handle.
     */
    fun onEvent(event: UiEvent) {
        when (event) {
            UiEvent.AddToDo -> {
                addToDo()
            }

            is UiEvent.EditToDo -> {
                editToDo(toDo = event.toDo)
            }

            is UiEvent.SaveToDo -> {
                saveToDo(toDo = event.toDo)
            }

            UiEvent.DismissBottomSheet -> {
                dismissBottomSheet()
            }

            is UiEvent.ToggleCompleteToDo -> {
                toggleCompleteToDo(toDo = event.toDo)
            }

            UiEvent.DeleteAllToDos -> {
                updateDialogDisplay(toggle = true)
            }

            is UiEvent.DeleteToDo -> {
                deleteToDo(toDo = event.toDo)
            }

            is UiEvent.ReorderToDos -> {
                reorderToDos(toDos = event.toDos)
            }

            is UiEvent.RestoreToDo -> {
                restoreToDo(toDo = event.toDo)
            }

            UiEvent.ConfirmDialog -> {
                deleteAllToDos()
                updateDialogDisplay(toggle = false)
            }

            UiEvent.CancelDialog -> {
                updateDialogDisplay(toggle = false)
            }

            UiEvent.ToggleOffIsFirstLaunch -> {
                toggleOffIsFirstLaunch()
            }
        }
    }


    private suspend fun setIsFirstLaunch() {
        _uiState.value = _uiState.value.copy(
            isFirstLaunch = useCases.retrieveIsFirstLaunchUseCase()
        )
    }

    private suspend fun setSampleToDos() {
        useCases.setSampleToDosUseCase()
    }

    private fun addToDo() {
        _uiState.value = _uiState.value.copy(
            activeToDo = null,
            showBottomSheet = true
        )
    }

    private fun editToDo(toDo: ToDo) {
        _uiState.value = _uiState.value.copy(
            activeToDo = toDo,
            showBottomSheet = true
        )
    }

    private fun saveToDo(toDo: ToDo) {
        viewModelScope.launch(context = dispatcher) {
            useCases.saveToDoUseCase(toDo = toDo)
        }
        _uiState.value = _uiState.value.copy(
            activeToDo = null,
            showBottomSheet = false
        )
    }

    private fun dismissBottomSheet() {
        _uiState.value = _uiState.value.copy(
            activeToDo = null,
            showBottomSheet = false
        )
    }

    private fun toggleCompleteToDo(toDo: ToDo) {
        viewModelScope.launch(context = dispatcher) {
            useCases.toggleCompleteToDoUseCase(toDo = toDo)
        }
    }

    private fun deleteToDo(toDo: ToDo) {
        viewModelScope.launch(context = dispatcher) {
            useCases.deleteToDoUseCase(toDo = toDo)
        }
    }

    private fun deleteAllToDos() {
        viewModelScope.launch(context = dispatcher) {
            useCases.deleteAllToDosUseCase()
        }
    }

    private fun updateToDos(toDos: List<ToDo>) {
        _uiState.value = _uiState.value.copy(
            toDos = toDos
        )
    }

    private fun reorderToDos(toDos: List<ToDo>) {
        viewModelScope.launch(context = dispatcher) {
            useCases.reorderToDosUseCase(toDos = toDos)
        }
    }

    private fun restoreToDo(toDo: ToDo) {
        viewModelScope.launch(context = dispatcher) {
            useCases.restoreToDoUseCase(toDo = toDo)
        }
    }

    private fun updateDialogDisplay(toggle: Boolean) {
        _uiState.value = _uiState.value.copy(
            showDialog = toggle
        )
    }

    private fun toggleOffIsFirstLaunch() {
        viewModelScope.launch(context = dispatcher) {
            useCases.toggleOffIsFirstLaunchUseCase()
            _uiState.value = _uiState.value.copy(
                isFirstLaunch = false
            )
        }
    }
}
