package com.nicoqueijo.android.todolist.domain.usecases

import com.nicoqueijo.android.todolist.data.Repository
import com.nicoqueijo.android.todolist.domain.model.ToDo
import javax.inject.Inject

class ReorderToDosUseCase @Inject constructor(
    private val repository: Repository,
) {

    suspend operator fun invoke(toDos: List<ToDo>) {
        toDos.forEachIndexed { index, toDo ->
            toDo.position = index
        }
        repository.upsertToDos(toDos = toDos)
    }
}