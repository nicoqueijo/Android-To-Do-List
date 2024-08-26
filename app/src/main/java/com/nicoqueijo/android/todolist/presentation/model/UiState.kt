package com.nicoqueijo.android.todolist.presentation.model

import com.nicoqueijo.android.todolist.domain.model.ToDo

/**
 * Represents the state of the UI for the To-Do list feature.
 *
 * @property toDos The list of [ToDo] items to be displayed.
 * @property showDialog Indicates whether a dialog should be shown.
 * @property showBottomSheet Indicates whether the bottom sheet should be shown.
 * @property activeToDo The [ToDo] item currently being edited.
 * @property isFirstLaunch Flag indicating if the application is being launched for the first time.
 */
data class UiState(
    val toDos: List<ToDo> = emptyList(),
    val showDialog: Boolean = false,
    val showBottomSheet: Boolean = false,
    val activeToDo: ToDo? = null,
    val isFirstLaunch: Boolean = true,
)
