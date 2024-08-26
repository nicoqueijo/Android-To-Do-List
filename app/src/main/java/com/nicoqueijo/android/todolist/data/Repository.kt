package com.nicoqueijo.android.todolist.data

import com.nicoqueijo.android.todolist.domain.model.ToDo
import kotlinx.coroutines.flow.Flow

/**
 * A repository interface that defines the data operations for the To-Do app.
 *
 * This interface abstracts the underlying data sources and provides a clean API
 * for the rest of the application to interact with To-Do related data.
 */
interface Repository {

    /**
     * Inserts or updates a single To-Do item in the data source.
     *
     * @param toDo The [ToDo] item to be inserted or updated.
     */
    suspend fun upsertToDo(toDo: ToDo)

    /**
     * Inserts or updates a list of To-Do items in the data source.
     *
     * @param toDos The list of [ToDo] items to be inserted or updated.
     */
    suspend fun upsertToDos(toDos: List<ToDo>)

    /**
     * Deletes a specific To-Do item from the data source.
     *
     * @param toDo The [ToDo] item to be deleted.
     */
    suspend fun deleteToDo(toDo: ToDo)

    /**
     * Deletes all To-Do items from the data source.
     */
    suspend fun deleteAllToDos()

    /**
     * Retrieves all To-Do items from the data source as a flow.
     *
     * @return A [Flow] emitting a list of [ToDo] items.
     */
    suspend fun getAllToDos(): Flow<List<ToDo>>

    /**
     * Retrieves the total count of To-Do items in the data source.
     *
     * @return The number of To-Do items.
     */
    suspend fun getToDosCount(): Int

    /**
     * Sets whether the application is being launched for the first time.
     *
     * @param value A boolean indicating whether it is the first launch.
     */
    suspend fun setFirstLaunch(value: Boolean)

    /**
     * Checks whether the application is being launched for the first time.
     *
     * @return A boolean indicating whether it is the first launch.
     */
    suspend fun isFirstLaunch(): Boolean
}
