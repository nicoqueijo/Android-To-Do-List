package com.nicoqueijo.android.branch.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class ToDo(
    @PrimaryKey(autoGenerate = true)
    val id: Int? = null,
    val position: Int,
    val title: String,
    val description: String? = null,
    val isCompleted: Boolean = false,
)
