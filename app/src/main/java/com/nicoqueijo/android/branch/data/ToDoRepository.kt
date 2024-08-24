package com.nicoqueijo.android.branch.data

import com.nicoqueijo.android.branch.core.di.IODispatcher
import com.nicoqueijo.android.branch.domain.model.ToDo
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class ToDoRepository @Inject constructor(
    private val toDoDao: ToDoDao,
    @IODispatcher private val dispatcher: CoroutineDispatcher,
) : Repository {

    override suspend fun upsertToDo(toDo: ToDo) {
        withContext(context = dispatcher) {
            toDoDao.upsertToDo(toDo = toDo)
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

    override suspend fun getAllToDos(): List<ToDo> {
        return withContext(context = dispatcher) {
            toDoDao.getAllToDos()
        }
    }

    override suspend fun getToDosCount(): Int {
        return withContext(context = dispatcher) {
            toDoDao.getToDosCount()
        }
    }
}