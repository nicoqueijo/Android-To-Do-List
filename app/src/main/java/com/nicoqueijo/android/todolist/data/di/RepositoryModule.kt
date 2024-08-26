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

/**
 * A Dagger module to provide dependencies related to the repository layer.
 *
 * This module is installed in the SingletonComponent scope, ensuring that the provided
 * dependencies are available throughout the entire lifecycle of the application.
 */
@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    /**
     * Provides a singleton instance of [Repository].
     *
     * The repository handles data operations and acts as a single source of truth
     * for the app's data. It uses [ToDoDao] for database operations, [DataStoreManager]
     * for data storage, and a [CoroutineDispatcher] for performing tasks in the
     * I/O thread.
     *
     * @param toDoDao The [ToDoDao] instance used for accessing the To-Do database.
     * @param dataStoreManager The [DataStoreManager] instance used for managing data storage.
     * @param dispatcher The [CoroutineDispatcher] used for performing I/O operations.
     * @return An instance of [Repository], specifically a [ToDoRepository].
     */
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
