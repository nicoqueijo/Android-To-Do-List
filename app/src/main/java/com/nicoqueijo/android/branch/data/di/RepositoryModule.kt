package com.nicoqueijo.android.branch.data.di

import com.nicoqueijo.android.branch.core.di.IODispatcher
import com.nicoqueijo.android.branch.data.Repository
import com.nicoqueijo.android.branch.data.ToDoDao
import com.nicoqueijo.android.branch.data.ToDoRepository
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
        @IODispatcher dispatcher: CoroutineDispatcher,
    ): Repository {
        return ToDoRepository(
            toDoDao = toDoDao,
            dispatcher = dispatcher
        )
    }
}