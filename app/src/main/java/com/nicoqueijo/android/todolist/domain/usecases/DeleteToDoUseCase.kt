package com.nicoqueijo.android.todolist.domain.usecases

import com.nicoqueijo.android.todolist.data.Repository
import com.nicoqueijo.android.todolist.domain.model.ToDo
import kotlinx.coroutines.flow.first
import javax.inject.Inject

/**
 * Use case for deleting a specific To-Do item.
 *
 * @property repository The [Repository] instance used to access and modify To-Do data.
 */
class DeleteToDoUseCase @Inject constructor(
    private val repository: Repository,
) {

    /**
     * Retrieves the current list of To-Do items, updates their positions to reflect the removal
     * of the specified item, and then deletes the item from the repository.
     *
     * @param toDo The [ToDo] item to be deleted.
     */
    suspend operator fun invoke(toDo: ToDo) {
        val toDos = repository.getAllToDos().first()
        for (i in toDos.count() - 1 downTo toDo.position + 1) {
            toDos[i].position = --toDos[i].position
        }
        repository.upsertToDos(toDos)
        repository.deleteToDo(toDo = toDo)
    }
}
