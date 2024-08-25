package com.nicoqueijo.android.todolist.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class ToDo(
    @PrimaryKey(autoGenerate = true)
    val id: Int? = null,
    var position: Int = -1,
    val title: String,
    val description: String? = null,
    val isCompleted: Boolean = false,
)
