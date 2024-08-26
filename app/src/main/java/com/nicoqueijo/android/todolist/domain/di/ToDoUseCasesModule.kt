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

@Module
@InstallIn(ViewModelComponent::class)
object ToDoUseCasesModule {

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