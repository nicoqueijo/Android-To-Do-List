package com.nicoqueijo.android.branch

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class ToDo(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val title: String,
    val description: String,
    val isCompleted: Boolean = false,
    // val date: ???
)
