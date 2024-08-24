package com.nicoqueijo.android.branch.data

import com.nicoqueijo.android.branch.domain.model.ToDo

interface Repository {
    suspend fun upsertToDo(toDo: ToDo)
    suspend fun deleteToDo(toDo: ToDo)
    suspend fun deleteAllToDos()
    suspend fun getAllToDos(): List<ToDo>
    suspend fun getToDosCount(): Int
}