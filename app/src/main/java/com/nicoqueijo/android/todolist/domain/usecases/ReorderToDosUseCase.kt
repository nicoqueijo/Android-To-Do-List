package com.nicoqueijo.android.todolist.domain.usecases

import com.nicoqueijo.android.todolist.data.Repository
import com.nicoqueijo.android.todolist.domain.model.ToDo
import javax.inject.Inject

/**
 * Use case for reordering a list of To-Do items.
 *
 * @property repository The [Repository] instance used to access and modify To-Do data.
 */
class ReorderToDosUseCase @Inject constructor(
    private val repository: Repository,
) {

    /**
     * Updates the position of each To-Do item in the list to reflect the new order and saves the
     * updated list to the repository.
     *
     * @param toDos The list of [ToDo] items to be reordered.
     */
    suspend operator fun invoke(toDos: List<ToDo>) {
        toDos.forEachIndexed { index, toDo ->
            toDo.position = index
        }
        repository.upsertToDos(toDos = toDos)
    }
}
