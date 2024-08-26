package com.nicoqueijo.android.todolist.domain.di

import com.nicoqueijo.android.todolist.data.Repository
import com.nicoqueijo.android.todolist.domain.usecases.DeleteAllToDosUseCase
import com.nicoqueijo.android.todolist.domain.usecases.DeleteToDoUseCase
import com.nicoqueijo.android.todolist.domain.usecases.ReorderToDosUseCase
import com.nicoqueijo.android.todolist.domain.usecases.RestoreToDoUseCase
import com.nicoqueijo.android.todolist.domain.usecases.RetrieveIsFirstLaunchUseCase
import com.nicoqueijo.android.todolist.domain.usecases.RetrieveToDosUseCase
import com.nicoqueijo.android.todolist.domain.usecases.SaveToDoUseCase
import com.nicoqueijo.android.todolist.domain.usecases.SetSampleToDosUseCase
import com.nicoqueijo.android.todolist.domain.usecases.ToDoUseCases
import com.nicoqueijo.android.todolist.domain.usecases.ToggleCompleteToDoUseCase
import com.nicoqueijo.android.todolist.domain.usecases.ToggleOffIsFirstLaunchUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

/**
 * Dagger module that provides [ToDoUseCases] for dependency injection.
 *
 * This module is responsible for providing instances of the various use cases required by the
 * application. These use cases encapsulate business logic related to To-Do operations and are
 * injected into the ViewModel using Dagger Hilt. The module is installed in the [ViewModelComponent]
 * to ensure that the provided use cases are scoped to the lifecycle of the ViewModel.
 */
@Module
@InstallIn(ViewModelComponent::class)
object ToDoUseCasesModule {

    /**
     * Provides a [ToDoUseCases] instance that aggregates all the individual use cases required
     * by the application.
     *
     * This method initializes each use case with the [Repository] dependency and returns a
     * [ToDoUseCases] instance containing all the use cases.
     *
     * @param repository The [Repository] instance used by the use cases for data operations.
     * @return An instance of [ToDoUseCases] that aggregates all the To-Do related use cases.
     */
    @Provides
    fun provideToDoUseCases(
        repository: Repository,
    ): ToDoUseCases {
        return ToDoUseCases(
            saveToDoUseCase = SaveToDoUseCase(repository = repository),
            retrieveToDosUseCase = RetrieveToDosUseCase(repository = repository),
            toggleCompleteToDoUseCase = ToggleCompleteToDoUseCase(repository = repository),
            deleteAllToDosUseCase = DeleteAllToDosUseCase(repository = repository),
            deleteToDoUseCase = DeleteToDoUseCase(repository = repository),
            restoreToDoUseCase = RestoreToDoUseCase(repository = repository),
            reorderToDosUseCase = ReorderToDosUseCase(repository = repository),
            setSampleToDosUseCase = SetSampleToDosUseCase(repository = repository),
            retrieveIsFirstLaunchUseCase = RetrieveIsFirstLaunchUseCase(repository = repository),
            toggleOffIsFirstLaunchUseCase = ToggleOffIsFirstLaunchUseCase(repository = repository),
        )
    }
}
