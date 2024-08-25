package com.nicoqueijo.android.todolist.data

import com.nicoqueijo.android.todolist.domain.model.ToDo
import kotlinx.coroutines.flow.Flow

interface Repository {
    suspend fun upsertToDo(toDo: ToDo)
    suspend fun upsertToDos(toDos: List<ToDo>)
    suspend fun deleteToDo(toDo: ToDo)
    suspend fun deleteAllToDos()
    suspend fun getAllToDos(): Flow<List<ToDo>>
    suspend fun getToDosCount(): Int
}