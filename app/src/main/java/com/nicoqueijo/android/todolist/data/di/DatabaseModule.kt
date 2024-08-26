package com.nicoqueijo.android.todolist.data.di

import android.content.Context
import androidx.room.Room
import com.nicoqueijo.android.todolist.data.ToDoDao
import com.nicoqueijo.android.todolist.data.ToDoDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * A Dagger module to provide the dependencies related to the Room database.
 *
 * This module is installed in the SingletonComponent scope, ensuring that the
 * provided dependencies are available throughout the entire lifecycle of the application.
 */
@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    /**
     * Provides a singleton instance of the [ToDoDatabase].
     *
     * @param context The application context used to initialize the database.
     * @return An instance of [ToDoDatabase] initialized with Room database builder.
     */
    @Singleton
    @Provides
    fun provideToDoDatabase(@ApplicationContext context: Context): ToDoDatabase {
        return Room.databaseBuilder(
            context = context,
            klass = ToDoDatabase::class.java,
            name = "toDo.db"
        ).fallbackToDestructiveMigration().build()
    }

    /**
     * Provides a singleton instance of [ToDoDao].
     *
     * This DAO is used to perform CRUD operations on the To-Do entities stored in the database.
     *
     * @param database The [ToDoDatabase] instance from which the DAO is obtained.
     * @return An instance of [ToDoDao] for accessing the To-Do table.
     */
    @Singleton
    @Provides
    fun provideToDoDao(database: ToDoDatabase): ToDoDao {
        return database.toDoDao()
    }
}
