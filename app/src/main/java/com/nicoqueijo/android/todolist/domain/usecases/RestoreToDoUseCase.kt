package com.nicoqueijo.android.todolist.domain.usecases

import com.nicoqueijo.android.todolist.data.Repository
import com.nicoqueijo.android.todolist.domain.model.ToDo
import kotlinx.coroutines.flow.first
import javax.inject.Inject

class RestoreToDoUseCase @Inject constructor(
    private val repository: Repository,
) {
    suspend operator fun invoke(toDo: ToDo) {
        val toDos = repository.getAllToDos().first()
        for (i in toDo.position until toDos.size) {
            toDos[i].position = ++toDos[i].position
        }
        repository.upsertToDos(
            toDos = (toDos + toDo)
        )
    }
}