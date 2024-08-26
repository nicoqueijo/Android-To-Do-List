package com.nicoqueijo.android.todolist.domain.model

/**
 * Represents different user interface events in the application.
 *
 * This sealed interface defines various events that can occur in the UI layer of the application.
 * Each event represents a specific user action or interaction that the application needs to handle.
 */
sealed interface UiEvent {

    /**
     * Event triggered to add a new To-Do item.
     */
    data object AddToDo : UiEvent

    /**
     * Event triggered to edit an existing To-Do item.
     *
     * @property toDo The [ToDo] item that is to be edited.
     */
    data class EditToDo(val toDo: ToDo) : UiEvent

    /**
     * Event triggered to save a To-Do item.
     *
     * @property toDo The [ToDo] item that is to be saved.
     */
    data class SaveToDo(val toDo: ToDo) : UiEvent

    /**
     * Event triggered to dismiss the bottom sheet.
     */
    data object DismissBottomSheet : UiEvent

    /**
     * Event triggered to toggle the completion status of a To-Do item.
     *
     * @property toDo The [ToDo] item whose completion status is to be toggled.
     */
    data class ToggleCompleteToDo(val toDo: ToDo) : UiEvent

    /**
     * Event triggered to delete all To-Do items.
     */
    data object DeleteAllToDos : UiEvent

    /**
     * Event triggered to delete a specific To-Do item.
     *
     * @property toDo The [ToDo] item that is to be deleted.
     */
    data class DeleteToDo(val toDo: ToDo) : UiEvent

    /**
     * Event triggered to restore a deleted To-Do item.
     *
     * @property toDo The [ToDo] item that is to be restored.
     */
    data class RestoreToDo(val toDo: ToDo) : UiEvent

    /**
     * Event triggered to reorder a list of To-Do items.
     *
     * @property toDos The list of [ToDo] items to be reordered.
     */
    data class ReorderToDos(val toDos: List<ToDo>) : UiEvent

    /**
     * Event triggered to show a confirmation dialog.
     */
    data object ConfirmDialog : UiEvent

    /**
     * Event triggered to cancel a dialog.
     */
    data object CancelDialog : UiEvent

    /**
     * Event triggered to toggle off the first launch status.
     */
    data object ToggleOffIsFirstLaunch : UiEvent
}
