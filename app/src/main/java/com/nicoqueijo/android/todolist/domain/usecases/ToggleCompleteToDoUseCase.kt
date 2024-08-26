package com.nicoqueijo.android.todolist.domain.usecases

import com.nicoqueijo.android.todolist.data.Repository
import com.nicoqueijo.android.todolist.domain.model.ToDo
import javax.inject.Inject

/**
 * Use case for toggling the completion status of a To-Do item.
 *
 * @property repository The [Repository] instance used to access and modify To-Do data.
 */
class ToggleCompleteToDoUseCase @Inject constructor(
    private val repository: Repository,
) {

    /**
     * Updates the To-Do item's `isCompleted` status to its opposite value and saves the updated
     * item in the repository.
     *
     * @param toDo The [ToDo] item whose completion status is to be toggled.
     */
    suspend operator fun invoke(toDo: ToDo) {
        repository.upsertToDo(
            toDo = toDo.copy(
                isCompleted = !toDo.isCompleted
            )
        )
    }
}
