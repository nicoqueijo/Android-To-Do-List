package com.nicoqueijo.android.todolist.domain.usecases

data class ToDoUseCases(
    val saveToDoUseCase: SaveToDoUseCase,
    val retrieveToDosUseCase: RetrieveToDosUseCase,
    val completeToDosUseCase: ToggleCompleteToDoUseCase,
    val deleteAllToDosUseCase: DeleteAllToDosUseCase,
    val deleteToDoUseCase: DeleteToDoUseCase,
    val restoreToDoUseCase: RestoreToDoUseCase,
)
