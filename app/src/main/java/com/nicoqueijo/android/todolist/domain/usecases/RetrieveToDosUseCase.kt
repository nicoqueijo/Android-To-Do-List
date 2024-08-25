package com.nicoqueijo.android.todolist.domain.usecases

import com.nicoqueijo.android.todolist.data.Repository
import com.nicoqueijo.android.todolist.domain.model.ToDo
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class RetrieveToDosUseCase @Inject constructor(
    private val repository: Repository,
) {

    suspend operator fun invoke(): Flow<List<ToDo>> {
        return repository.getAllToDos()
    }
}