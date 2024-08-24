package com.nicoqueijo.android.branch.data

import com.nicoqueijo.android.branch.domain.model.ToDo
import kotlinx.coroutines.flow.Flow

interface Repository {
    suspend fun upsertToDo(toDo: ToDo)
    suspend fun deleteToDo(toDo: ToDo)
    suspend fun deleteAllToDos()
    suspend fun getAllToDos(): Flow<List<ToDo>>
    suspend fun getToDosCount(): Int
}