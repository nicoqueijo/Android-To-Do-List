package com.nicoqueijo.android.branch.data

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.nicoqueijo.android.branch.domain.model.ToDo

@Dao
interface ToDoDao {

    @Upsert
    suspend fun upsertToDo(toDo: ToDo)

    @Query("SELECT * FROM ToDo ORDER BY position ASC")
    suspend fun getAllToDos(): List<ToDo>

    @Query("SELECT COUNT(*) FROM ToDo")
    suspend fun getToDosCount(): Int
}