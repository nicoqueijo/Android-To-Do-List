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
                TODO()
            }
            UiEvent.CancelDialog -> {
                TODO()
            }
            UiEvent.CompleteToDo -> {
                TODO()
            }
            UiEvent.ConfirmDialog -> {
                TODO()
            }
            UiEvent.DeleteAllToDos -> {
                TODO()
            }
            UiEvent.DeleteToDo -> {
                TODO()
            }
            UiEvent.EditToDo -> {
                TODO()
            }
            UiEvent.ReorderToDos -> {
                TODO()
            }
            UiEvent.RestoreToDo -> {
                TODO()
            }
            UiEvent.SaveToDo -> {
                TODO()
            }
            UiEvent.ToggleOffIsFirstLaunch -> {
                TODO()
            }
        }
    }
}