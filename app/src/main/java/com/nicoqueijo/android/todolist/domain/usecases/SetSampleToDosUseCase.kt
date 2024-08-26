package com.nicoqueijo.android.todolist.domain.usecases

import com.nicoqueijo.android.todolist.data.Repository
import com.nicoqueijo.android.todolist.domain.model.ToDo
import javax.inject.Inject

/**
 * Use case for setting up sample To-Do items during the first launch of the application.
 *
 * @property repository The [Repository] instance used to access and modify To-Do data.
 */
class SetSampleToDosUseCase @Inject constructor(
    private val repository: Repository,
) {

    /**
     * Inserts sample To-Do items into the repository if it is the first launch of the application.
     *
     * Checks the "is first launch" flag. If it is the first launch, a predefined list of sample
     * To-Do items is added to the repository.
     */
    suspend operator fun invoke() {
        if (repository.isFirstLaunch()) {
            val sampleToDos = listOf(
                ToDo(
                    position = 0,
                    title = "Grocery Shopping",
                    description = "Buy groceries for the week, including fruits, vegetables, milk, bread, and eggs.",
                ),
                ToDo(
                    position = 1,
                    title = "Plan Weekend Trip",
                    description = "Research destinations for a weekend trip, book a hotel, and plan activities.",
                ),
                ToDo(
                    position = 2,
                    title = "Morning Exercise",
                    description = "Go for a 30-minute run in the park and complete a 15-minute stretching session.",
                ),
            )
            repository.upsertToDos(toDos = sampleToDos)
        }
    }
}
