package com.nicoqueijo.android.todolist.domain.usecases

import com.nicoqueijo.android.todolist.data.Repository
import com.nicoqueijo.android.todolist.domain.model.ToDo
import kotlinx.coroutines.flow.first
import javax.inject.Inject

class DeleteToDoUseCase @Inject constructor(
    private val repository: Repository,
) {
    suspend operator fun invoke(toDo: ToDo) {
        val toDos = repository.getAllToDos().first()
        for (i in toDos.count() - 1 downTo toDo.position + 1) {
            toDos[i].position = --toDos[i].position
        }
        repository.upsertToDos(toDos)
        repository.deleteToDo(toDo = toDo)
    }
}
