package com.nicoqueijo.android.todolist.presentation.model

import com.nicoqueijo.android.todolist.domain.model.ToDo

data class UiState(
    val toDos: List<ToDo> = emptyList(),
    val showDialog: Boolean = false,
    val isFirstLaunch: Boolean = true,
)
