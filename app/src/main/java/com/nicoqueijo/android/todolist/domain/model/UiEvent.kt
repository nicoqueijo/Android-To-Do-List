package com.nicoqueijo.android.todolist.domain.model

sealed interface UiEvent {
    data object AddToDo: UiEvent
    data object EditToDo: UiEvent
    data object SaveToDo: UiEvent
    data object CompleteToDo: UiEvent
    data object DeleteAllToDos: UiEvent
    data object DeleteToDo: UiEvent
    data object RestoreToDo: UiEvent
    data object ReorderToDos: UiEvent
    data object ConfirmDialog : UiEvent
    data object CancelDialog : UiEvent
    data object ToggleOffIsFirstLaunch : UiEvent
}