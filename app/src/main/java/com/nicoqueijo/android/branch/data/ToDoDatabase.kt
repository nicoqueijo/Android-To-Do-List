package com.nicoqueijo.android.branch.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.nicoqueijo.android.branch.domain.model.ToDo

@Database(entities = [ToDo::class], version = 1)
abstract class ToDoDatabase : RoomDatabase() {

    abstract fun toDoDao(): ToDoDao
}