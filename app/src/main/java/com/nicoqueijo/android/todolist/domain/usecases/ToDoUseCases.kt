package com.nicoqueijo.android.todolist.domain.usecases

import com.nicoqueijo.android.todolist.domain.model.ToDo

/**
 * Holds all the use cases related to To-Do items.
 *
 * @property saveToDoUseCase Use case for saving a To-Do item.
 * @property retrieveToDosUseCase Use case for retrieving all To-Do items.
 * @property toggleCompleteToDoUseCase Use case for toggling the completion status of a To-Do item.
 * @property deleteAllToDosUseCase Use case for deleting all To-Do items.
 * @property deleteToDoUseCase Use case for deleting a specific To-Do item.
 * @property restoreToDoUseCase Use case for restoring a deleted To-Do item.
 * @property reorderToDosUseCase Use case for reordering a list of To-Do items.
 * @property setSampleToDosUseCase Use case for setting up sample To-Do items during the first launch.
 * @property retrieveIsFirstLaunchUseCase Use case for checking if it is the first launch of the application.
 * @property toggleOffIsFirstLaunchUseCase Use case for marking that the application has been launched for the first time.
 */
data class ToDoUseCases(
    val saveToDoUseCase: SaveToDoUseCase,
    val retrieveToDosUseCase: RetrieveToDosUseCase,
    val toggleCompleteToDoUseCase: ToggleCompleteToDoUseCase,
    val deleteAllToDosUseCase: DeleteAllToDosUseCase,
    val deleteToDoUseCase: DeleteToDoUseCase,
    val restoreToDoUseCase: RestoreToDoUseCase,
    val reorderToDosUseCase: ReorderToDosUseCase,
    val setSampleToDosUseCase: SetSampleToDosUseCase,
    val retrieveIsFirstLaunchUseCase: RetrieveIsFirstLaunchUseCase,
    val toggleOffIsFirstLaunchUseCase: ToggleOffIsFirstLaunchUseCase,
)
