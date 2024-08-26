package com.nicoqueijo.android.todolist.domain.usecases

import com.nicoqueijo.android.todolist.data.Repository
import com.nicoqueijo.android.todolist.domain.model.ToDo
import javax.inject.Inject

/**
 * Use case for saving a To-Do item.
 *
 * @property repository The [Repository] instance used to access and modify To-Do data.
 */
class SaveToDoUseCase @Inject constructor(
    private val repository: Repository,
) {

    /**
     * Saves the specified To-Do item to the repository.
     *
     * If the To-Do item has an unspecified position (`position == -1`), it is assigned a new
     * position based on the current count of To-Do items in the repository.
     *
     * @param toDo The [ToDo] item to be saved.
     */
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
