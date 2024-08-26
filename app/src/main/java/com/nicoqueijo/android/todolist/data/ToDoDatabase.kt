package com.nicoqueijo.android.todolist.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.nicoqueijo.android.todolist.domain.model.ToDo

/**
 * The Room database for the To-Do application.
 *
 * This abstract class represents the main access point to the persisted To-Do data using Room.
 * It defines the database configuration and serves as the main connection to the app's data
 * for Room. It is annotated with `@Database` to specify the entities included in the database
 * and the version number.
 */
@Database(entities = [ToDo::class], version = 1)
abstract class ToDoDatabase : RoomDatabase() {

    /**
     * Provides access to the DAO (Data Access Object) for the To-Do database.
     *
     * @return An instance of [ToDoDao] that handles all CRUD operations related to the
     * To-Do entities in the database.
     */
    abstract fun toDoDao(): ToDoDao
}
