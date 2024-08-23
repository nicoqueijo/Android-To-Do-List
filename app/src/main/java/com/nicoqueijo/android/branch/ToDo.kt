package com.nicoqueijo.android.branch

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class ToDo(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val order: Int,
    val title: String,
    val description: String? = null,
    val isCompleted: Boolean = false,
)
