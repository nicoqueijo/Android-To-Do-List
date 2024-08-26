package com.nicoqueijo.android.todolist.data

import com.nicoqueijo.android.todolist.core.di.IODispatcher
import com.nicoqueijo.android.todolist.domain.model.ToDo
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import javax.inject.Inject

class ToDoRepository @Inject constructor(
    private val toDoDao: ToDoDao,
    private val dataStoreManager: DataStoreManager,
    @IODispatcher private val dispatcher: CoroutineDispatcher,
) : Repository {

    override suspend fun upsertToDo(toDo: ToDo) {
        withContext(context = dispatcher) {
            toDoDao.upsertToDo(toDo = toDo)
        }
    }

    override suspend fun upsertToDos(toDos: List<ToDo>) {
        withContext(context = dispatcher) {
            toDoDao.upsertToDos(toDos = toDos)
        }
    }

    override suspend fun deleteToDo(toDo: ToDo) {
        withContext(context = dispatcher) {
            toDoDao.deleteToDo(toDo = toDo)
        }
    }

    override suspend fun deleteAllToDos() {
        withContext(context = dispatcher) {
            toDoDao.deleteAllToDos()
        }
    }

    override suspend fun getAllToDos(): Flow<List<ToDo>> {
        return withContext(context = dispatcher) {
            toDoDao.getAllToDos()
        }
    }

    override suspend fun getToDosCount(): Int {
        return withContext(context = dispatcher) {
            toDoDao.getToDosCount()
        }
    }

    override suspend fun setFirstLaunch(value: Boolean) {
        withContext(context = dispatcher) {
            dataStoreManager.setFirstLaunch(value = value)
        }
    }

    override suspend fun isFirstLaunch(): Boolean {
        return withContext(context = dispatcher) {
            dataStoreManager.isFirstLaunch()
        }
    }
}