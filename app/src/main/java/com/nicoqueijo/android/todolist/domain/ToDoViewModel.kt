package com.nicoqueijo.android.todolist.domain

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nicoqueijo.android.todolist.core.di.DefaultDispatcher
import com.nicoqueijo.android.todolist.domain.model.UiEvent
import com.nicoqueijo.android.todolist.presentation.model.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ToDoViewModel @Inject constructor(
    /*private val useCases: ToDoUseCases,*/
    @DefaultDispatcher private val dispatcher: CoroutineDispatcher,
) : ViewModel() {

    private val _uiState = MutableStateFlow(value = UiState())
    val uiState = _uiState.asStateFlow()

    init {
        /*setIsFirstLaunch()*/
        /*setSampleToDos()*/
        viewModelScope.launch(context = dispatcher) {
            /*val toDos = useCases.retrieveToDosUseCase()
            toDos.collectLatest { databaseToDos ->
                updateToDos()
            }*/
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
                    showBottomSheet = true,
                )
            }
            UiEvent.CancelDialog -> TODO()
            UiEvent.CompleteToDo -> TODO()
            UiEvent.ConfirmDialog -> TODO()
            UiEvent.DeleteAllToDos -> TODO()
            UiEvent.DeleteToDo -> TODO()
            UiEvent.DismissBottomSheet -> {
                // Move logic to private function & use case
                _uiState.value = _uiState.value.copy(
                    showBottomSheet = false
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
            UiEvent.RestoreToDo -> TODO()

            UiEvent.ToggleOffIsFirstLaunch -> TODO()
            is UiEvent.SaveToDo -> {
                val toDo = event.toDo
                _uiState.value = _uiState.value.copy(
                    activeToDo = null,
                    showBottomSheet = false,
                )
            }
        }
    }
}