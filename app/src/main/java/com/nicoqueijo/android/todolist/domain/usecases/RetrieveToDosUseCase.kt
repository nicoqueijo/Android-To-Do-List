package com.nicoqueijo.android.todolist.domain.usecases

import com.nicoqueijo.android.todolist.data.Repository
import com.nicoqueijo.android.todolist.domain.model.ToDo
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

/**
 * Use case for retrieving all To-Do items.
 *
 * @property repository The [Repository] instance used to access and modify To-Do data.
 */
class RetrieveToDosUseCase @Inject constructor(
    private val repository: Repository,
) {

    /**
     * Retrieves a flow of all To-Do items from the repository.
     *
     * @return A [Flow] emitting the list of [ToDo] items.
     */
    suspend operator fun invoke(): Flow<List<ToDo>> {
        return repository.getAllToDos()
    }
}
