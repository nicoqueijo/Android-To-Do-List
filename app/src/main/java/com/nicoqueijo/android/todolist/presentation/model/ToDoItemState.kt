package com.nicoqueijo.android.todolist.presentation.model

import com.nicoqueijo.android.todolist.domain.model.ToDo

data class ToDoItemState(
    val toDo: ToDo,
    val onEdit: ((ToDo) -> Unit)? = null, // Open bottom sheet with title and description pre-filled from ToDo
    val onDrag: (() -> Unit)? = null, // Need the Reorderable library to do this
    val onCheck: ((Boolean) -> Unit)? = null, // Mark the ToDo as completed and upsert it to the db (screen should update automatically)
    val onRemove: ((ToDo) -> Unit)? = null, // Delete the ToDo using the Dao (screen should update automatically)
)
