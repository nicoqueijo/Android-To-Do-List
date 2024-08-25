package com.nicoqueijo.android.todolist.domain.model

sealed interface UiEvent {
    data object AddToDo: UiEvent // Invokes bottom sheet with title & description fields empty & Save button on bottom right corner that is disabled unless the title is not empty or blank.
    data object EditToDo: UiEvent // Invokes bottom sheet with title & description fields of selected ToDo
    data object SaveToDo: UiEvent // Dismiss bottom sheet and save ToDo to the db
    data object DismissBottomSheet : UiEvent // Dismiss bottom sheet and save ToDo to the db
    data object CompleteToDo: UiEvent // Save the clicked ToDo to db with a flipped isCompleted field
    data object DeleteAllToDos: UiEvent // Delete all ToDos from db
    data object DeleteToDo: UiEvent // Delete selected ToDo from db
    data object RestoreToDo: UiEvent
    data object ReorderToDos: UiEvent
    data object ConfirmDialog : UiEvent
    data object CancelDialog : UiEvent
    data object ToggleOffIsFirstLaunch : UiEvent
}