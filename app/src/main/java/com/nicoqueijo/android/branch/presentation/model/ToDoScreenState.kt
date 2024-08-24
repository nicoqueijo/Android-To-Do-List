package com.nicoqueijo.android.branch.presentation.model

data class ToDoScreenState(
    val toDoStates: List<ToDoItemState> = emptyList(),
    val showDialog: Boolean = false,
    val isFirstLaunch: Boolean = true,
)
