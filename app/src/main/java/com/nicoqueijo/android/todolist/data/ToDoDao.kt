package com.nicoqueijo.android.todolist.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import com.nicoqueijo.android.todolist.domain.model.ToDo
import kotlinx.coroutines.flow.Flow

@Dao
interface ToDoDao {

    @Upsert
    suspend fun upsertToDo(toDo: ToDo)

    @Delete
    suspend fun deleteToDo(toDo: ToDo)

    @Query("DELETE FROM ToDo")
    suspend fun deleteAllToDos()

    @Query("SELECT * FROM ToDo ORDER BY position ASC")
    fun getAllToDos(): Flow<List<ToDo>>

    @Query("SELECT COUNT(*) FROM ToDo")
    suspend fun getToDosCount(): Int
}