package com.nicoqueijo.android.todolist.domain.usecases

import com.nicoqueijo.android.todolist.data.Repository
import com.nicoqueijo.android.todolist.domain.model.ToDo
import javax.inject.Inject

class DeleteToDoUseCase @Inject constructor(
    private val repository: Repository,
) {
    suspend operator fun invoke(toDo: ToDo) {
        repository.deleteToDo(toDo = toDo)
    }
}
