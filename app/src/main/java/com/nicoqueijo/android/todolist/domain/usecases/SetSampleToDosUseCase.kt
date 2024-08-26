package com.nicoqueijo.android.todolist.domain.usecases

import com.nicoqueijo.android.todolist.data.Repository
import com.nicoqueijo.android.todolist.domain.model.ToDo
import javax.inject.Inject

class SetSampleToDosUseCase @Inject constructor(
    private val repository: Repository,
) {

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