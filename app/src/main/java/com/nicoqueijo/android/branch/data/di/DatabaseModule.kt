package com.nicoqueijo.android.branch.data.di

import android.content.Context
import androidx.room.Room
import com.nicoqueijo.android.branch.data.ToDoDao
import com.nicoqueijo.android.branch.data.ToDoDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Singleton
    @Provides
    fun provideToDoDatabase(@ApplicationContext context: Context): ToDoDatabase {
        return Room.databaseBuilder(
            context = context,
            klass = ToDoDatabase::class.java,
            name = "toDo.db"
        ).fallbackToDestructiveMigration().build()
    }

    @Singleton
    @Provides
    fun provideToDoDao(database: ToDoDatabase): ToDoDao {
        return database.toDoDao()
    }
}