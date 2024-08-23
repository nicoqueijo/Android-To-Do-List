package com.nicoqueijo.android.branch

data class ToDo(
    val id: Int? = null,
    val title: String,
    val description: String,
    val isCompleted: Boolean = false,
    // val date: ???
)
