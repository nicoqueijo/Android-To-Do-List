package com.nicoqueijo.android.todolist.domain.usecases

import com.nicoqueijo.android.todolist.data.Repository
import javax.inject.Inject

/**
 * Use case for deleting all To-Do items.
 * This use case encapsulates the logic for removing all To-Do items from the repository.
 *
 * @property repository The [Repository] instance used to access and modify To-Do data.
 */
class DeleteAllToDosUseCase @Inject constructor(
    private val repository: Repository,
) {

    /**
     * Deletes all To-Do items by calling the [Repository].
     */
    suspend operator fun invoke() {
        repository.deleteAllToDos()
    }
}
