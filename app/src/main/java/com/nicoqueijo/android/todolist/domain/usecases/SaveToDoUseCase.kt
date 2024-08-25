package com.nicoqueijo.android.todolist.domain.usecases

import com.nicoqueijo.android.todolist.data.Repository
import com.nicoqueijo.android.todolist.domain.model.ToDo
import javax.inject.Inject

class SaveToDoUseCase @Inject constructor(
    private val repository: Repository,
) {
    suspend operator fun invoke(toDo: ToDo) {
        val position = if (toDo.position == -1) {
            repository.getToDosCount()
        } else {
            toDo.position
        }
        repository.upsertToDo(
            toDo = toDo.copy(position = position)
        )
    }
}