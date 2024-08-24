package com.nicoqueijo.android.branch.presentation.model

import com.nicoqueijo.android.branch.domain.model.ToDo

data class ToDoState(
    val toDo: ToDo,
    val onEdit: ((ToDo) -> Unit)? = null,
    val onDrag: (() -> Unit)? = null,
    val onCheck: ((Boolean) -> Unit)? = null,
    val onRemove: ((ToDo) -> Unit)? = null,
)
