package com.nicoqueijo.android.todolist.domain.usecases

import com.nicoqueijo.android.todolist.data.Repository
import com.nicoqueijo.android.todolist.domain.model.ToDo
import kotlinx.coroutines.flow.first
import javax.inject.Inject

/**
 * Use case for restoring a previously deleted To-Do item.
 *
 * @property repository The [Repository] instance used to access and modify To-Do data.
 */
class RestoreToDoUseCase @Inject constructor(
    private val repository: Repository,
) {

    /**
     * Retrieves the current list of To-Do items, adjusts the positions of items to make room for
     * the restored To-Do, adds the To-Do item back into the list, and then updates the repository.
     *
     * @param toDo The [ToDo] item to be restored.
     */
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
