package com.nicoqueijo.android.todolist.data

import com.nicoqueijo.android.todolist.core.di.IODispatcher
import com.nicoqueijo.android.todolist.domain.model.ToDo
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import javax.inject.Inject

/**
 * A repository implementation for managing To-Do data operations.
 *
 * This class implements the [Repository] interface and provides methods to interact with the
 * To-Do data source. It uses [ToDoDao] for database operations and [DataStoreManager] for
 * managing app-specific settings like first launch status. It also handles background execution
 * using the provided [CoroutineDispatcher].
 *
 * @property toDoDao The [ToDoDao] instance for performing database operations.
 * @property dataStoreManager The [DataStoreManager] instance for managing app-specific settings.
 * @property dispatcher The [CoroutineDispatcher] used for performing I/O operations.
 */
class ToDoRepository @Inject constructor(
    private val toDoDao: ToDoDao,
    private val dataStoreManager: DataStoreManager,
    @IODispatcher private val dispatcher: CoroutineDispatcher,
) : Repository {

    /**
     * Inserts or updates a single To-Do item in the database.
     *
     * This method performs the operation in the context of the provided [dispatcher] to
     * ensure it runs on the appropriate thread for I/O operations.
     *
     * @param toDo The [ToDo] item to be inserted or updated.
     */
    override suspend fun upsertToDo(toDo: ToDo) {
        withContext(context = dispatcher) {
            toDoDao.upsertToDo(toDo = toDo)
        }
    }

    /**
     * Inserts or updates a list of To-Do items in the database.
     *
     * This method performs the operation in the context of the provided [dispatcher] to
     * ensure it runs on the appropriate thread for I/O operations.
     *
     * @param toDos The list of [ToDo] items to be inserted or updated.
     */
    override suspend fun upsertToDos(toDos: List<ToDo>) {
        withContext(context = dispatcher) {
            toDoDao.upsertToDos(toDos = toDos)
        }
    }

    /**
     * Deletes a specific To-Do item from the database.
     *
     * This method performs the operation in the context of the provided [dispatcher] to
     * ensure it runs on the appropriate thread for I/O operations.
     *
     * @param toDo The [ToDo] item to be deleted.
     */
    override suspend fun deleteToDo(toDo: ToDo) {
        withContext(context = dispatcher) {
            toDoDao.deleteToDo(toDo = toDo)
        }
    }

    /**
     * Deletes all To-Do items from the database.
     *
     * This method performs the operation in the context of the provided [dispatcher] to
     * ensure it runs on the appropriate thread for I/O operations.
     */
    override suspend fun deleteAllToDos() {
        withContext(context = dispatcher) {
            toDoDao.deleteAllToDos()
        }
    }

    /**
     * Retrieves all To-Do items from the database.
     *
     * This method returns a [Flow] of the list of To-Do items, allowing for reactive updates.
     * It performs the operation in the context of the provided [dispatcher] to ensure it
     * runs on the appropriate thread for I/O operations.
     *
     * @return A [Flow] emitting a list of [ToDo] items.
     */
    override suspend fun getAllToDos(): Flow<List<ToDo>> {
        return withContext(context = dispatcher) {
            toDoDao.getAllToDos()
        }
    }

    /**
     * Retrieves the count of To-Do items in the database.
     *
     * This method performs the operation in the context of the provided [dispatcher] to
     * ensure it runs on the appropriate thread for I/O operations.
     *
     * @return The count of To-Do items in the database.
     */
    override suspend fun getToDosCount(): Int {
        return withContext(context = dispatcher) {
            toDoDao.getToDosCount()
        }
    }

    /**
     * Sets whether the application is being launched for the first time.
     *
     * This method updates the first launch status in the [DataStoreManager] and performs the
     * operation in the context of the provided [dispatcher] to ensure it runs on the appropriate
     * thread for I/O operations.
     *
     * @param value A boolean indicating whether it is the first launch.
     */
    override suspend fun setFirstLaunch(value: Boolean) {
        withContext(context = dispatcher) {
            dataStoreManager.setFirstLaunch(value = value)
        }
    }

    /**
     * Checks whether the application is being launched for the first time.
     *
     * This method retrieves the first launch status from the [DataStoreManager] and performs the
     * operation in the context of the provided [dispatcher] to ensure it runs on the appropriate
     * thread for I/O operations.
     *
     * @return A boolean indicating whether it is the first launch.
     */
    override suspend fun isFirstLaunch(): Boolean {
        return withContext(context = dispatcher) {
            dataStoreManager.isFirstLaunch()
        }
    }
}
