package com.nicoqueijo.android.todolist.domain.model

sealed interface UiEvent {
    data object AddToDo : UiEvent
    data class EditToDo(val toDo: ToDo) : UiEvent
    data class SaveToDo(val toDo: ToDo) : UiEvent
    data object DismissBottomSheet : UiEvent
    data class ToggleCompleteToDo(val toDo: ToDo) : UiEvent
    data object DeleteAllToDos : UiEvent
    data class DeleteToDo(val toDo: ToDo) : UiEvent
    data object RestoreToDo : UiEvent
    data object ReorderToDos : UiEvent
    data object ConfirmDialog : UiEvent
    data object CancelDialog : UiEvent
    data object ToggleOffIsFirstLaunch : UiEvent
}