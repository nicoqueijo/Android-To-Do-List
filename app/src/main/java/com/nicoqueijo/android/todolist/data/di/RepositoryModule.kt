package com.nicoqueijo.android.todolist.data.di

import com.nicoqueijo.android.todolist.core.di.IODispatcher
import com.nicoqueijo.android.todolist.data.DataStoreManager
import com.nicoqueijo.android.todolist.data.Repository
import com.nicoqueijo.android.todolist.data.ToDoDao
import com.nicoqueijo.android.todolist.data.ToDoRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Singleton
    @Provides
    fun provideRepository(
        toDoDao: ToDoDao,
        dataStoreManager: DataStoreManager,
        @IODispatcher dispatcher: CoroutineDispatcher,
    ): Repository {
        return ToDoRepository(
            toDoDao = toDoDao,
            dataStoreManager = dataStoreManager,
            dispatcher = dispatcher
        )
    }
}