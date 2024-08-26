package com.nicoqueijo.android.todolist.domain.usecases

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
