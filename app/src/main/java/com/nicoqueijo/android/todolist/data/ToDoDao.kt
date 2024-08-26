package com.nicoqueijo.android.todolist.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import com.nicoqueijo.android.todolist.domain.model.ToDo
import kotlinx.coroutines.flow.Flow

/**
 * Data Access Object (DAO) for managing CRUD operations on the To-Do table.
 *
 * This interface provides methods for inserting, updating, deleting, and retrieving To-Do items
 * from the database. It is annotated with `@Dao` to let Room generate the necessary code for interacting
 * with the database.
 */
@Dao
interface ToDoDao {

    /**
     * Inserts or updates a single To-Do item in the database.
     *
     * If the To-Do item already exists, it is updated; otherwise, it is inserted.
     *
     * @param toDo The [ToDo] item to be inserted or updated.
     */
    @Upsert
    suspend fun upsertToDo(toDo: ToDo)

    /**
     * Inserts or updates a list of To-Do items in the database.
     *
     * If a To-Do item already exists, it is updated; otherwise, it is inserted.
     *
     * @param toDos The list of [ToDo] items to be inserted or updated.
     */
    @Upsert
    suspend fun upsertToDos(toDos: List<ToDo>)

    /**
     * Deletes a specific To-Do item from the database.
     *
     * @param toDo The [ToDo] item to be deleted.
     */
    @Delete
    suspend fun deleteToDo(toDo: ToDo)

    /**
     * Deletes all To-Do items from the database.
     *
     * This method clears the entire To-Do table, removing all entries.
     */
    @Query("DELETE FROM ToDo")
    suspend fun deleteAllToDos()

    /**
     * Retrieves all To-Do items from the database, ordered by position in ascending order.
     *
     * The result is returned as a [Flow], which allows for reactive data handling and
     * automatically updates any observers when the data changes.
     *
     * @return A [Flow] emitting a list of [ToDo] items.
     */
    @Query("SELECT * FROM ToDo ORDER BY position ASC")
    fun getAllToDos(): Flow<List<ToDo>>

    /**
     * Retrieves the total count of To-Do items in the database.
     *
     * @return The number of To-Do items stored in the database.
     */
    @Query("SELECT COUNT(*) FROM ToDo")
    suspend fun getToDosCount(): Int
}
